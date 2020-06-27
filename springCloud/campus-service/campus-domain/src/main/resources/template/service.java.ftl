package ${package.Service};

import ${package.Entity}.${entity};
import ${package.Mapper}.I${entity}Repository;
import ${superServiceImplClassPackage};
import org.springframework.stereotype.Service;

/**
 * <p>
 * ${table.comment!} 服务实现类
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
@Service
<#if kotlin>
open class ${table.serviceImplName} : ${superServiceImplClass}<${table.mapperName}, ${entity}>(), ${table.serviceName} {

}
<#else>
public class ${entity}Service extends ${superServiceImplClass}<I${entity}Repository, ${entity}> {

}
</#if>
