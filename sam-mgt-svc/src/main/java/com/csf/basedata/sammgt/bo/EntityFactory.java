package com.csf.basedata.sammgt.bo;

import com.csf.basedata.sammgt.domain.entity.samclient.*;
import org.apache.commons.collections4.CollectionUtils;

import java.util.*;


/**
 * Created by bob.huang on 2019/12/3
 */
public class EntityFactory {

    private EntityFactory() {

    }

    public static ScmProduct convertFromProductBo(ScmProductBo bo) {
        ScmProduct entity = null;
        if (bo != null) {
            entity = new ScmProduct();
            entity.setId(bo.getId());
            entity.setCode(bo.getCode());
            entity.setNameZh(bo.getNameZh());
            entity.setNameEn(bo.getNameEn());
            entity.setParent(bo.getParent());
            entity.setAncestors(bo.getAncestors());
            entity.setLevel(bo.getLevel());
            entity.setSource(bo.getSource());
            entity.setStatus(bo.getStatus());
            entity.setVersionId(bo.getVersionId());
            entity.setPublisher(bo.getPublisher());
            entity.setCreatedBy(bo.getCreatedBy());
            entity.setUpdatedBy(bo.getUpdatedBy());
            entity.setDescEn(bo.getDescEn());
            entity.setDescZh(bo.getDescZh());
            entity.setRemark(bo.getRemark());
            entity.setSequence(bo.getSequence());
            entity.setTreeId(bo.getTreeId());
        }

        return entity;
    }

    public static Collection<ScmProductMapping> convertFromScmProductMappingBo(Collection<ScmProductMappingBo> bos) {
        List<ScmProductMapping> entityList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(bos)) {
            bos.forEach(bo -> Optional.ofNullable(convertFromScmProductMappingBo(bo)).ifPresent(entityList::add));
        }

        return entityList;
    }

    public static ScmProductMapping convertFromScmProductMappingBo(ScmProductMappingBo bo) {
        ScmProductMapping entity = null;
        if (bo != null) {
            entity = new ScmProductMapping();
            //entity.setId(bo.getId());
            entity.setCsfProductCode(bo.getCsfProductCode());
            entity.setCsfProductName(bo.getCsfProductCodeName());
            entity.setCsfProductNameEn(bo.getCsfProductCodeNameEn());
            entity.setCsfProductParentCode(bo.getCsfProductParentCode());
            entity.setThirdPartyCode(bo.getThirdPartyCode());
            entity.setStatus(bo.getStatus());
            //entity.setCreatedBy(bo.getCreatedBy());
           // entity.setPublisher(bo.getPublisher());
            //entity.setUpdatedBy(bo.getUpdatedBy());
            entity.setTreeId(bo.getTreeId());
        }

        return entity;
    }


    public static ScmProductStream convertFromScmProductStreamBo(ScmProductStreamBo bo) {
        ScmProductStream entity = null;
        if (bo != null) {
            entity = new ScmProductStream();
            entity.setUpstreamCode(bo.getUpstreamCode());
            entity.setUpstreamType(bo.getUpstreamType());
            entity.setDownstreamCode(bo.getDownstreamCode());
            entity.setDownstreamType(bo.getDownstreamType());
            entity.setDirection(bo.getDirection());
            entity.setWeight(bo.getWeight());
            entity.setStatus(bo.getStatus());
            entity.setUpdatedTime(bo.getUpdatedTime());
            entity.setUpdatedBy(bo.getUpdatedBy());
            entity.setCreatedBy(bo.getCreatedBy());
            entity.setCreatedTime(bo.getCreatedTime());
            entity.setId(bo.getId());
            entity.setRemark(bo.getRemark());
            entity.setPublisher(bo.getPublisher());
        }
        return entity;
    }

    public static List<ScmProductStream> convertFromScmProductStreamBo(List<ScmProductStreamBo> boList) {
        List<ScmProductStream> entityList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(boList)) {
            boList.forEach(bo -> Optional.ofNullable(convertFromScmProductStreamBo(bo)).ifPresent(entityList::add));
        }
        return entityList;
    }

    public static ProductBaseEntity convertFromProductBaseBo(ScmProductBaseBo bo) {
        ProductBaseEntity entity = null;
        if (bo != null) {
            entity = new ProductBaseEntity();
            entity.setCode(bo.getCode());
            entity.setVersionId(bo.getVersionId());
        }
        return entity;
    }

    public static List<ProductBaseEntity> convertFromProductBaseBo(List<ScmProductBaseBo> boList) {
        List<ProductBaseEntity> entityList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(boList)) {
            boList.forEach(bo -> Optional.ofNullable(convertFromProductBaseBo(bo)).ifPresent(entityList::add));
        }
        return entityList;
    }

    public static ScmProductTree convertFromScmProductTreeBo(ScmProductTreeBo bo) {
        ScmProductTree entity = null;
        if (bo != null) {
            entity = new ScmProductTree();
            entity.setId(bo.getId());
            entity.setName(bo.getName());
            entity.setDescription(bo.getDescription());
            entity.setStatus(bo.getStatus());
            entity.setViewType(bo.getViewType());
            entity.setUpdatedTime(bo.getUpdatedTime());
            entity.setUpdatedBy(bo.getUpdatedBy());
            entity.setCreatedBy(bo.getCreatedBy());
            entity.setCreatedTime(bo.getCreatedTime());
            entity.setCsfIgnoreTime(new Date());
        }
        return entity;
    }

    public static List<ScmProductTree> convertFromScmProductTreeBo(List<ScmProductTreeBo> boList) {
        List<ScmProductTree> entityList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(boList)) {
            boList.forEach(bo -> Optional.ofNullable(convertFromScmProductTreeBo(bo)).ifPresent(entityList::add));
        }
        return entityList;
    }

    public static ScmProductRelation convertFromScmProductRelationBo(ScmProductRelationBo bo) {
        ScmProductRelation entity = null;
        if (bo != null) {
            entity = new ScmProductRelation();
            entity.setId(bo.getId());
            entity.setStartProduct(bo.getStartProduct());
            entity.setEndProduct(bo.getEndProduct());
            entity.setRelation(bo.getRelation());
            entity.setTreeId(bo.getTreeId());
            entity.setStatus(bo.getStatus());
            entity.setUpdatedTime(bo.getUpdatedTime());
            entity.setUpdatedBy(bo.getUpdatedBy());
            entity.setCreatedBy(bo.getCreatedBy());
            entity.setCreatedTime(bo.getCreatedTime());
        }
        return entity;
    }

    public static List<ScmProductRelation> convertFromScmProductRelationBo(List<ScmProductRelationBo> boList) {
        List<ScmProductRelation> entityList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(boList)) {
            boList.forEach(bo -> Optional.ofNullable(convertFromScmProductRelationBo(bo)).ifPresent(entityList::add));
        }
        return entityList;
    }
}
