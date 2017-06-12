package com.luis.wms.web.action;

import com.luis.wms.query.QueryObject;
import com.luis.wms.query.ClientQueryObject;
import lombok.Getter;
import lombok.Setter;

import com.luis.wms.domain.Client;
import com.luis.wms.service.IClientService;
import com.luis.wms.util.RequiredPermission;

public class ClientAction extends BaseAction {
    private static final long serialVersionUID = 1L;

    @Setter
    private IClientService clientService;

    @Getter
    private Client client = new Client();

    @Getter
    private QueryObject qo = new ClientQueryObject();

    @RequiredPermission("客户列表")
    public String execute() throws Exception {
        try {
            putContext("pageResult", clientService.query(qo));
        } catch (Exception e) {
            e.printStackTrace();
            super.addActionError(e.getMessage());
        }
        return "list";
    }

    @RequiredPermission("客户新增/更改")
    public String input() throws Exception {
        if (client.getId() != null) {
            client = clientService.get(client.getId());
        }
        return INPUT;
    }

    @RequiredPermission("客户保存/更新")
    public String saveOrUpdate() {
        try {
            if (client.getId() == null) {
                clientService.save(client);
                super.addActionMessage("保存成功");
            } else {
                clientService.update(client);
                super.addActionMessage("更新成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            super.addActionError(e.getMessage());//添加错误
        }
        return SUCCESS;
    }

    @RequiredPermission("客户删除")
    public String delete() throws Exception {
        if (client.getId() != null) {
            clientService.delete(client);
        }
        return NONE;
    }

    public void prepareSaveOrUpdate() throws Exception {
        if (client.getId() != null) {
            client = clientService.get(client.getId());
        }
    }
}
