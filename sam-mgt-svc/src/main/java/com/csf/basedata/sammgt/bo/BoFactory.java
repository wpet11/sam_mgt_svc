package com.csf.basedata.sammgt.bo;


import com.csf.basedata.sammgt.domain.entity.samclient.ScmProduct;
import com.csf.basedata.sammgt.domain.entity.samclient.ScmProductRelation;
import com.csf.basedata.sammgt.domain.entity.samclient.ScmProductStream;
import com.csf.basedata.sammgt.domain.entity.samclient.ScmProductTree;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author michelle.min
 */
public class BoFactory {
    private BoFactory() {

    }

    public static ScmProductStreamBo convertFromScmProductStream(ScmProductStream entity) {
        ScmProductStreamBo bo = null;
        if (entity != null) {
            bo = new ScmProductStreamBo();
            bo.setUpstreamCode(entity.getUpstreamCode());
            bo.setUpstreamType(entity.getUpstreamType());
            bo.setDownstreamCode(entity.getDownstreamCode());
            bo.setDownstreamType(entity.getDownstreamType());
            bo.setDirection(entity.getDirection());
            bo.setWeight(entity.getWeight());
            bo.setUpdatedTime(entity.getUpdatedTime());
            bo.setUpdatedBy(entity.getUpdatedBy());
            bo.setCreatedBy(entity.getCreatedBy());
            bo.setCreatedTime(entity.getCreatedTime());
            bo.setId(entity.getId());
            bo.setStatus(entity.getStatus());
            bo.setPublisher(entity.getPublisher());
        }
        return bo;
    }

    public static List<ScmProductStreamBo> convertFromScmProductStream(List<ScmProductStream> entityList) {
        List<ScmProductStreamBo> boList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(entityList)) {
            entityList.forEach(entity -> Optional.ofNullable(convertFromScmProductStream(entity)).ifPresent(boList::add));
        }
        return boList;
    }

    public static List<ScmProductBo> convertFromScmProduct(List<ScmProduct> entityList) {
        List<ScmProductBo> boList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(entityList)) {
            entityList.forEach(entity -> Optional.ofNullable(convertFromScmProduct(entity)).ifPresent(boList::add));
        }
        return boList;
    }

    public static ScmProductBo convertFromScmProduct(ScmProduct entity) {
        ScmProductBo bo = null;
        if (entity != null) {
            bo = new ScmProductBo();
            bo.setId(entity.getId());
            bo.setCode(entity.getCode());
            bo.setNameEn(entity.getNameEn());
            bo.setNameZh(entity.getNameZh());
            bo.setId(entity.getId());
            bo.setLevel(entity.getLevel());
            bo.setParent(entity.getParent());
            bo.setPublisher(entity.getPublisher());
            bo.setStatus(entity.getStatus());
            bo.setVersionId(entity.getVersionId());
            bo.setAncestors(entity.getAncestors());
            bo.setCreatedBy(entity.getCreatedBy());
            bo.setUpdatedBy(entity.getUpdatedBy());
            bo.setCreatedTime(entity.getCreatedTime());
            bo.setUpdatedTime(entity.getUpdatedTime());
            bo.setSource(entity.getSource());
            bo.setDescZh(entity.getDescZh());
            bo.setDescEn(entity.getDescEn());
            bo.setSequence(entity.getSequence());
            bo.setTreeId(entity.getTreeId());
        }
        return bo;
    }

//    public static List<ScmProductMappingBo> convertFromScmProductMapping(List<ScmProductMapping> entityList) {
//        List<ScmProductMappingBo> bos = new ArrayList<>();
//        if (CollectionUtils.isNotEmpty(entityList)) {
//            entityList.forEach(entity -> Optional.ofNullable(convertFromScmProductMapping(entity)).ifPresent(bos::add));
//        }
//
//        return bos;
//    }

//    public static ScmProductMappingBo convertFromScmProductMapping(ScmProductMapping entity) {
//        ScmProductMappingBo bo = null;
//        if (entity != null) {
//            bo = new ScmProductMappingBo();
//            bo.setCsfProductCode(entity.getCsfProductCode());
//            bo.setThirdPartyCode(entity.getThirdPartyCode());
//            bo.setStatus(entity.getStatus());
//            bo.setId(entity.getId());
//            bo.setPublisher(entity.getPublisher());
//            bo.setUpdatedBy(entity.getUpdatedBy());
//            bo.setCreatedBy(entity.getCreatedBy());
//        }
//
//        return bo;
//    }

    public static ScmProductTreeBo convertFromScmProductTree(ScmProductTree entity) {
        ScmProductTreeBo bo = null;
        if (entity != null) {
            bo = new ScmProductTreeBo();
            bo.setId(entity.getId());
            bo.setName(entity.getName());
            bo.setDescription(entity.getDescription());
            bo.setStatus(entity.getStatus());
            bo.setViewType(entity.getViewType());
            bo.setUpdatedTime(entity.getUpdatedTime());
            bo.setUpdatedBy(entity.getUpdatedBy());
            bo.setCreatedBy(entity.getCreatedBy());
            bo.setCreatedTime(entity.getCreatedTime());
        }
        return bo;
    }

    public static List<ScmProductTreeBo> convertFromScmProductTree(List<ScmProductTree> entityList) {
        List<ScmProductTreeBo> boList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(entityList)) {
            entityList.forEach(entity -> Optional.ofNullable(convertFromScmProductTree(entity)).ifPresent(boList::add));
        }
        return boList;
    }

    public static ScmProductRelationBo convertFromScmProductRelation(ScmProductRelation entity) {
        ScmProductRelationBo bo = null;
        if (entity != null) {
            bo = new ScmProductRelationBo();
            bo.setId(entity.getId());
            bo.setStartProduct(entity.getStartProduct());
            bo.setEndProduct(entity.getEndProduct());
            bo.setRelation(entity.getRelation());
            bo.setTreeId(entity.getTreeId());
            bo.setStatus(entity.getStatus());
            bo.setUpdatedTime(entity.getUpdatedTime());
            bo.setUpdatedBy(entity.getUpdatedBy());
            bo.setCreatedBy(entity.getCreatedBy());
            bo.setCreatedTime(entity.getCreatedTime());
        }
        return bo;
    }

    public static List<ScmProductRelationBo> convertFromScmProductRelation(List<ScmProductRelation> entityList) {
        List<ScmProductRelationBo> boList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(entityList)) {
            entityList.forEach(entity -> Optional.ofNullable(convertFromScmProductRelation(entity)).ifPresent(boList::add));
        }
        return boList;
    }
}
