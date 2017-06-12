package ${basePkg}.service.impl;

import ${basePkg}.dao.I${className}DAO;
import ${basePkg}.domain.${className};
import ${basePkg}.query.PageResult;
import ${basePkg}.query.QueryObject;
import ${basePkg}.service.I${className}Service;
import lombok.Setter;

import java.util.List;

public class ${className}ServiceImpl implements I${className}Service {

    @Setter
    private I${className}DAO ${objectName}DAO;

    public void save(${className} entity) {
        ${objectName}DAO.save(entity);
    }

    public void update(${className} entity) {
        ${objectName}DAO.update(entity);
    }

    public void delete(${className} entity) {
        ${objectName}DAO.delete(entity);
    }

    public ${className} get(Long id) {
        return ${objectName}DAO.get(id);
    }

    public List<${className}> listAll() {
        return ${objectName}DAO.listAll();
    }

    public PageResult query(QueryObject qo) {
        return ${objectName}DAO.query(qo);
    }
}
