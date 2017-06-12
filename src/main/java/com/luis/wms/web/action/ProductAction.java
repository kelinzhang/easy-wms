package com.luis.wms.web.action;

import com.luis.wms.domain.Product;
import com.luis.wms.query.ProductQueryObject;
import com.luis.wms.query.QueryObject;
import com.luis.wms.service.IBrandService;
import com.luis.wms.service.IProductService;
import com.luis.wms.util.FileUploadUtil;
import com.luis.wms.util.RequiredPermission;
import com.opensymphony.xwork2.ActionContext;
import lombok.Getter;
import lombok.Setter;

import java.io.File;

public class ProductAction extends BaseAction {
    private static final long serialVersionUID = 1L;

    @Setter
    private IProductService productService;
    @Setter
    private IBrandService brandService;

    @Getter
    private Product product = new Product();

    @Getter
    private QueryObject qo = new ProductQueryObject();

    //图片的原地址
    @Setter
    private File pic;
    //图片的带扩展名的名称
    @Setter
    private String picFileName;

    @RequiredPermission("货品列表")
    public String execute() throws Exception {
        try {
            putContext("brands",brandService.listAll());
            ActionContext.getContext().put("pageResult", productService.query(qo));
        } catch (Exception e) {
            e.printStackTrace();
            super.addActionError(e.getMessage());
        }
        return "list";
    }

    @RequiredPermission("货品选择列表")
    public String selectProductList() throws Exception {
        try {
            qo.setPageSize(20);
            putContext("brands",brandService.listAll());
            ActionContext.getContext().put("pageResult", productService.query(qo));
        } catch (Exception e) {
            e.printStackTrace();
            super.addActionError(e.getMessage());
        }
        return "selectProductList";
    }

    @RequiredPermission("货品新增/更改")
    public String input() throws Exception {
        putContext("brands",brandService.listAll());
        if (product.getId() != null) {
            product = productService.get(product.getId());
        }
        return INPUT;
    }

    @RequiredPermission("货品保存/更新")
    public String saveOrUpdate() {
        try {
            if(product.getId() != null && pic != null){
                FileUploadUtil.deleteFile(product.getImagePath());
            }
            if(pic != null){
                String picPath = FileUploadUtil.uploadFile(pic,picFileName);
                product.setImagePath(picPath);
            }
            if (product.getId() == null) {
                productService.save(product);
                super.addActionMessage("保存成功");
            } else {
                productService.update(product);
                super.addActionMessage("更新成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            super.addActionError(e.getMessage());//添加错误
        }
        return SUCCESS;
    }

    @RequiredPermission("货品删除")
    public String delete() throws Exception {
        if (product.getId() != null) {
            product = productService.get(product.getId());
            if (product.getImagePath() != null) {
                FileUploadUtil.deleteFile(product.getImagePath());
            }
            productService.delete(product);
        }
        return NONE;
    }

    public void prepareSaveOrUpdate() throws Exception {
        if (product.getId() != null) {
            product = productService.get(product.getId());
        }
    }
}
