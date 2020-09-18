package com.ssl.manager.modules.base.controller;


import cn.hutool.core.date.DateUnit;
import cn.hutool.core.util.ArrayUtil;
import com.ssl.manager.vo.DefaultEnumDict;
import com.ssl.manager.vo.EnumDict;
import com.ssl.manager.vo.Result;
import org.flywaydb.core.internal.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xiaojunnuo
 * @since 2018-06-20
 */
@RestController
@RequestMapping("/base/dicts")
public class DictsController  {

    @Autowired
    private ResourceLoader resourceLoader;



    /**
     */
    @RequestMapping(value = "/",method = {RequestMethod.POST,RequestMethod.GET})
    public Result<Map<String,List<EnumDict>>> dicts(HttpServletResponse response) throws IOException, ClassNotFoundException {
        //5小时浏览器过期
        response.setDateHeader("Expires", System.currentTimeMillis()+ 5* DateUnit.DAY.getMillis());
        Map<String, List<EnumDict>> map = getEnumDicts();
        return Result.success("",map);
    }

    /**
     */
    @RequestMapping(value = "/{name}",method = {RequestMethod.POST,RequestMethod.GET})
    public Result<List<EnumDict>> dictByName(HttpServletResponse response, @PathVariable("name") String name) throws IOException, ClassNotFoundException {
        //5小时浏览器过期
        response.setDateHeader("Expires", System.currentTimeMillis()+5*DateUnit.DAY.getMillis());
        Map<String, List<EnumDict>> map = getEnumDicts();
        return Result.success("",map.get(name));
    }

    private Map<String, List<EnumDict>> getEnumDicts() throws IOException, ClassNotFoundException {
        List<Class> enumClasses = scanEnumDicts();
        Map<String,List<EnumDict>> map = new HashMap<>();
        for (Class item : enumClasses) {
            if(item.isEnum() && ArrayUtil.contains(item.getInterfaces(), EnumDict.class)){
                map.put(item.getSimpleName(), DefaultEnumDict.create(item) );
            }
        }
        return map;
    }


    private List<Class> scanEnumDicts() throws IOException, ClassNotFoundException {
        ResourcePatternResolver resolver = ResourcePatternUtils.getResourcePatternResolver(resourceLoader);
        MetadataReaderFactory metaReader = new CachingMetadataReaderFactory(resourceLoader);
        Resource[] resources = resolver.getResources("classpath*:com/veryreader/**/enums/**/*.class");
        List<Class> enums = new ArrayList<>();
        for (Resource r : resources) {
            MetadataReader reader = metaReader.getMetadataReader(r);
            enums.add(Class.forName(reader.getClassMetadata().getClassName()));
        }
        return enums;
    }
}
