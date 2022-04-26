package com.ataraxia.controller;

import com.ataraxia.domain.ResponseResult;
import com.ataraxia.domain.VideoDO;
import com.ataraxia.service.ElasticSearchService;
import com.ataraxia.util.FastDFSUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Ataraxia
 * @create 2022/4/20 23:08
 * @description 接口测试
 */

@RestController
public class RESTfulController {

    @Autowired
    private FastDFSUtil fastDFSUtil;

    @Autowired
    private ElasticSearchService elasticSearchService;

    private final Map<Integer, Map<String, Object>> dataMap;

    public RESTfulController() {
        dataMap = new HashMap<>();
        for (int i = 1; i < 3; i++) {
            Map<String, Object> data = new HashMap<>();
            data.put("id", i);
            data.put("name", "name" + i);
            dataMap.put(i, data);
        }
    }

    @GetMapping("objects/{id}")
    public Map<String, Object> getData(@PathVariable Integer id) {
        return dataMap.get(id);
    }

    @DeleteMapping("objects/{id}")
    public String deleteData(@PathVariable Integer id) {
        dataMap.remove(id);
        return "delete success！";
    }

    @PostMapping("objects")
    public String postData(@RequestBody Map<String, Object> data) {
        Integer[] idArrays = dataMap.keySet().toArray(new Integer[0]);
        Arrays.sort(idArrays);
        int nextId = idArrays[idArrays.length - 1] + 1;
        dataMap.put(nextId, data);
        return "post success！";
    }

    @PutMapping("objects")
    public String putData(@RequestBody Map<String, Object> data) {
        Integer id = Integer.valueOf(String.valueOf(data.get("id")));
        Map<String, Object> containedData = dataMap.get(id);
        if (containedData == null) {
            Integer[] idArrays = dataMap.keySet().toArray(new Integer[0]);
            Arrays.sort(idArrays);
            int nextId = idArrays[idArrays.length - 1] + 1;
            dataMap.put(nextId, data);
        } else {
            dataMap.put(id, data);
        }
        return "put success！";
    }

    @GetMapping("/slices")
    public void slices(MultipartFile file) {
        fastDFSUtil.convertFileToSlices(file);
    }

    @GetMapping("/es-videos")
    public ResponseResult<VideoDO> getEsVideos(@RequestParam String keyword) {
        VideoDO video = elasticSearchService.getVideos(keyword);
        return new ResponseResult<>(video);
    }

    @GetMapping("/contents")
    public ResponseResult<List<Map<String, Object>>> listContents(@RequestParam String keyword,
                                                                  @RequestParam Integer pageNo,
                                                                  @RequestParam Integer pageSize) throws Exception {
        List<Map<String, Object>> list = elasticSearchService.listContents(keyword, pageNo, pageSize);
        return new ResponseResult<>(list);
    }
}
