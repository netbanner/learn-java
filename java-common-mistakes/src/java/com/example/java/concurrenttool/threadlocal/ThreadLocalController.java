package com.example.java.concurrenttool.threadlocal;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
@RestController
@RequestMapping("/threadLocal")
public class ThreadLocalController {


    private static final ThreadLocal<Integer> num = ThreadLocal.withInitial(() -> null);


    @RequestMapping("wrong")
    public Map wrong(@RequestParam("id") Integer id) {
        Map map = getMap(id);
        return map;
    }

    private Map getMap(@RequestParam("id") Integer id) {
        String before = Thread.currentThread().getName() + ":" + num.get();
        num.set(id);
        String after = Thread.currentThread().getName() + ":" + num.get();

        Map map = new HashMap();
        map.put("before", before);
        map.put("after", after);
        return map;
    }

    /**
     *  threadLocl 再单一线程或者同意线程池的情况下可能回，出现重复使用，解决方案是，finally重新置空
     * @param id
     * @return
     */

    @RequestMapping("right")
    public Map right(@RequestParam("id") Integer id) {
        try {
            Map map = getMap(id);
            return map;
        } finally {
            num.remove();
        }
    }

}
