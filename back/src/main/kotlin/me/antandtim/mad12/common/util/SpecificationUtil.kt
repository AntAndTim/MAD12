package me.antandtim.mad12.common.util

import org.springframework.beans.BeanUtils
import org.springframework.data.jpa.domain.Specification

const val CLASS_LITERAL = "class"

inline fun <SpecType, reified Source> Source.toSpecification(): Specification<SpecType> {
    var specification: Specification<SpecType> = Specification.where(null)
    
    BeanUtils.getPropertyDescriptors(Source::class.java).forEach { descriptor ->
        val fieldName = descriptor.name
        if (fieldName == CLASS_LITERAL || isFieldExcluded<Source>(fieldName)) {
            return@forEach
        }
        val value: Any = descriptor.readMethod.invoke(this) ?: return@forEach
        specification =
            specification.and { root, _, cb -> cb.equal(root.get<Any>(fieldName), value) }
    }
    
    return specification
}

inline fun <reified Source> isFieldExcluded(fieldName: String) =
    Source::class.java
        .getDeclaredField(fieldName)
        .isAnnotationPresent(SpecificationExclusion::class.java)

@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
annotation class SpecificationExclusion