package com.luis.wms.web.action;

import com.luis.wms.query.QueryObject;
import com.luis.wms.query.BrandQueryObject;
import com.opensymphony.xwork2.ActionContext;
import lombok.Getter;
import lombok.Setter;

import com.luis.wms.domain.Brand;
import com.luis.wms.service.IBrandService;
import com.luis.wms.util.RequiredPermission;

public class BrandAction extends BaseAction {
    private static final long serialVersionUID = 1L;

    @Setter
    private IBrandService brandService;

    @Getter
    private Brand brand = new Brand();

    @Getter
    private QueryObject qo = new BrandQueryObject();

    @RequiredPermission("品牌列表")
    public String execute() throws Exception {
        try {
            ActionContext.getContext().put("pageResult", brandService.query(qo));
        } catch (Exception e) {
            e.printStackTrace();
            super.addActionError(e.getMessage());
        }
        return "list";
    }

    @RequiredPermission("品牌新增/更改")
    public String input() throws Exception {
        if (brand.getId() != null) {
            brand = brandService.get(brand.getId());
        }
        return INPUT;
    }

    @RequiredPermission("品牌保存/更新")
    public String saveOrUpdate() {
        try {
            if (brand.getId() == null) {
                brandService.save(brand);
                super.addActionMessage("保存成功");
            } else {
                brandService.update(brand);
                super.addActionMessage("更新成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            super.addActionError(e.getMessage());//添加错误
        }
        return SUCCESS;
    }

    @RequiredPermission("品牌删除")
    public String delete() throws Exception {
        if (brand.getId() != null) {
            brandService.delete(brand);
        }
        return NONE;
    }

    public void prepareSaveOrUpdate() throws Exception {
        if (brand.getId() != null) {
            brand = brandService.get(brand.getId());
        }
    }
}
