package com.finance.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 单数据源配置（jeecg.datasource.open = false时生效）
 *
 */
@Configuration
@MapperScan(value={"com.finance.modules.**.mapper*"})
public class MybatisPlusConfig {

    /**
         *  分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
    
//    /**
//     * mybatis-plus SQL执行效率插件【生产环境可以关闭】
//     */
//    @Bean
//    public PerformanceInterceptor performanceInterceptor() {
//        return new PerformanceInterceptor();
//    }
    
   
}
