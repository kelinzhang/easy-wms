package com.luis.wms.web.action;

import com.luis.wms.query.QueryObject;
import com.luis.wms.query.DepotQueryObject;
import lombok.Getter;
import lombok.Setter;

import com.luis.wms.domain.Depot;
import com.luis.wms.service.IDepotService;
import com.luis.wms.util.RequiredPermission;

public class DepotAction extends BaseAction {
    private static final long serialVersionUID = 1L;

    @Setter
    private IDepotService depotService;

    @Getter
    private Depot depot = new Depot();

    @Getter
    private QueryObject qo = new DepotQueryObject();

    @RequiredPermission("仓库列表")
    public String execute() throws Exception {
        try {
            putContext("pageResult", depotService.query(qo));
        } catch (Exception e) {
            e.printStackTrace();
            super.addActionError(e.getMessage());
        }
        return "list";
    }

    @RequiredPermission("仓库新增/更改")
    public String input() throws Exception {
        if (depot.getId() != null) {
            depot = depotService.get(depot.getId());
        }
        return INPUT;
    }

    @RequiredPermission("仓库保存/更新")
    public String saveOrUpdate() {
        try {
            if (depot.getId() == null) {
                depotService.save(depot);
                super.addActionMessage("保存成功");
            } else {
                depotService.update(depot);
                super.addActionMessage("更新成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            super.addActionError(e.getMessage());//添加错误
        }
        return SUCCESS;
    }

    @RequiredPermission("仓库删除")
    public String delete() throws Exception {
        if (depot.getId() != null) {
            depotService.delete(depot);
        }
        return NONE;
    }

    public void prepareSaveOrUpdate() throws Exception {
        if (depot.getId() != null) {
            depot = depotService.get(depot.getId());
        }
    }
}
