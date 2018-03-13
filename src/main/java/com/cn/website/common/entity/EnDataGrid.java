/* =============================================================
 * Created: [2016年3月15日] by Administrator
 * =============================================================
 *
 * Copyright 2014-2015 NetDragon Websoft Inc. All Rights Reserved
 *
 * =============================================================
 */

package com.cn.website.common.entity;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Administrator
 * @since
 */
public class EnDataGrid implements Serializable {

    /**
     * 表头展示
     */
    private static final long serialVersionUID = -4460872469391672530L;

    @JsonProperty("ID")
    private Integer id;

    @JsonProperty("frmName")
    private String sfrmName;

    @JsonProperty("useCode")
    private String suseCode;

    @JsonProperty("field")
    private String sfieldName;

    @JsonProperty("header")
    private String stitleCn;

    @JsonProperty("title")
    private String stitle;

    @JsonProperty("width")
    private Integer lwidth;

    @JsonProperty("frozen")
    private Integer bfrozen;

    @JsonProperty("hAlign")
    private String shAlign;

    @JsonProperty("align")
    private String salign;

    @JsonProperty("sortable")
    private Integer bsortable;

    @JsonProperty("order")
    private String sorder;

    @JsonProperty("resizable")
    private Integer bresizable;

    @JsonProperty("classname")
    private String sclassName;

    @JsonProperty("formatter")
    private String sformatter;

    @JsonProperty("templateId")
    private String stemplateId;

    @JsonProperty("template")
    private String stemplate;

    @JsonProperty("mapData")
    private String smapData;

    @JsonProperty("inx")
    private Integer linx;

    @JsonProperty("show")
    private Integer bshow;

    @JsonProperty("groupId")
    private Long groupId;

    @JsonProperty("require")
    private Integer brequire;

    public EnDataGrid() {

    }

    public EnDataGrid(String fieldName, String titleCn) {
        this.sfieldName = fieldName;
        this.stitleCn = titleCn;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSfrmName() {
        return sfrmName;
    }

    public void setSfrmName(String sfrmName) {
        this.sfrmName = sfrmName;
    }

    public String getSuseCode() {
        return suseCode;
    }

    public void setSuseCode(String suseCode) {
        this.suseCode = suseCode;
    }

    public String getSfieldName() {
        return sfieldName;
    }

    public void setSfieldName(String sfieldName) {
        this.sfieldName = sfieldName;
    }

    public String getStitleCn() {
        return stitleCn;
    }

    public void setStitleCn(String stitleCn) {
        this.stitleCn = stitleCn;
    }

    public Integer getLwidth() {
        return lwidth;
    }

    public void setLwidth(Integer lwidth) {
        this.lwidth = lwidth;
    }

    public boolean getBfrozen() {
        return bfrozen == 1 ? true : false;
    }

    public void setBfrozen(Boolean bfrozen) {
        this.bfrozen = (bfrozen == true ? 1 : 0);
    }

    public String getShAlign() {
        return shAlign;
    }

    public void setShAlign(String shAlign) {
        this.shAlign = shAlign;
    }

    public String getSalign() {
        return salign;
    }

    public void setSalign(String salign) {
        this.salign = salign;
    }

    public boolean getBsortable() {
        return bsortable == 1 ? true : false;
    }

    public void setBsortable(Boolean bsortable) {
        this.bsortable = (bsortable == true ? 1 : 0);
    }

    public String getSorder() {
        return sorder;
    }

    public void setSorder(String sorder) {
        this.sorder = sorder;
    }

    public boolean getBresizable() {
        return bresizable == 1 ? true : false;
    }

    public void setBresizable(Boolean bresizable) {
        this.bresizable = (bresizable == true ? 1 : 0);
    }

    public String getSclassName() {
        return sclassName;
    }

    public void setSclassName(String sclassName) {
        this.sclassName = sclassName;
    }

    public String getSformatter() {
        return sformatter;
    }

    public void setSformatter(String sformatter) {
        this.sformatter = sformatter;
    }

    public String getStemplateId() {
        return stemplateId;
    }

    public void setStemplateId(String stemplateId) {
        this.stemplateId = stemplateId;
    }

    public String getStemplate() {
        return stemplate;
    }

    public void setStemplate(String stemplate) {
        this.stemplate = stemplate;
    }

    public String getSmapData() {
        return smapData;
    }

    public void setSmapData(String smapData) {
        this.smapData = smapData;
    }

    public Integer getLinx() {
        return linx;
    }

    public void setLinx(Integer linx) {
        this.linx = linx;
    }

    public boolean getBshow() {
        return bshow == 1 ? true : false;
    }

    public void setBshow(Boolean bshow) {
        this.bshow = (bshow == true ? 1 : 0);
    }

    public String getStitle() {
        return stitle;
    }

    public void setStitle(String stitle) {
        this.stitle = stitle;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public Boolean getBrequire() {
        return brequire == 1 ? true : false;
    }

    public void setBrequire(Boolean brequire) {
        this.brequire = (brequire == true ? 1 : 0);
    }

}
