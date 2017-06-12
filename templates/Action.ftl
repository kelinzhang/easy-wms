package ${basePkg}.web.action;

import ${basePkg}.query.QueryObject;
import ${basePkg}.query.${className}QueryObject;
import lombok.Getter;
import lombok.Setter;

import ${basePkg}.domain.${className};
import ${basePkg}.service.I${className}Service;
import ${basePkg}.util.RequiredPermission;

public class ${className}Action extends BaseAction {
    private static final long serialVersionUID = 1L;

    @Setter
    private I${className}Service ${objectName}Service;

    @Getter
    private ${className} ${objectName} = new ${className}();

    @Getter
    private QueryObject qo = new ${className}QueryObject();

    @RequiredPermission("${className}列表")
    public String execute() throws Exception {
        try {
            putContext("pageResult", ${objectName}Service.query(qo));
        } catch (Exception e) {
            e.printStackTrace();
            super.addActionError(e.getMessage());
        }
        return "list";
    }

    @RequiredPermission("${className}新增/更改")
    public String input() throws Exception {
        if (${objectName}.getId() != null) {
            ${objectName} = ${objectName}Service.get(${objectName}.getId());
        }
        return INPUT;
    }

    @RequiredPermission("${className}保存/更新")
    public String saveOrUpdate() {
        try {
            if (${objectName}.getId() == null) {
                ${objectName}Service.save(${objectName});
                super.addActionMessage("保存成功");
            } else {
                ${objectName}Service.update(${objectName});
                super.addActionMessage("更新成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            super.addActionError(e.getMessage());//添加错误
        }
        return SUCCESS;
    }

    @RequiredPermission("${className}删除")
    public String delete() throws Exception {
        if (${objectName}.getId() != null) {
            ${objectName}Service.delete(${objectName});
        }
        return NONE;
    }

    public void prepareSaveOrUpdate() throws Exception {
        if (${objectName}.getId() != null) {
            ${objectName} = ${objectName}Service.get(${objectName}.getId());
        }
    }
}
