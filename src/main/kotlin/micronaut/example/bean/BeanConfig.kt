package micronaut.example.bean

import com.google.gson.Gson
import io.micronaut.context.annotation.Bean
import io.micronaut.context.annotation.Factory
import javax.inject.Singleton

@Factory
class BeanConfig {

    @Bean
    @Singleton
    fun gson(): Gson {
        return Gson()
    }
}