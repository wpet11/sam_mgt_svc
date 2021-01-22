package com.csf.basedata.sammgt.response;


import com.csf.basedata.sammgt.bo.Node;
import com.csf.basedata.sammgt.common.enums.ProductChainTypeEnum;
import lombok.Data;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author michelle.min
 */
@Data
public class ScmProductStreamGraphResp {
    private Collection<Node> nodes = new ArrayList<>();
    private Collection<Relationship> relationships = new ArrayList<>();

    public ScmProductStreamGraphResp(Collection<Node> nodes, Collection<Relationship> relationships) {
        this.nodes = nodes;
        this.relationships = relationships;
    }

    @Data
    public static class Relationship {
        private long id;
        private String startCode;
        private String startNameZh;
        private String startNameEn;
        private String endCode;
        private String endNameZh;
        private String endNameEn;
        private Integer weight;
        private String typeCode;
        private String typeName;
        private Integer direction;

        public Relationship(long id, String startCode, String startNameZh, String endCode, String endNameZh, Integer weight, String typeCode, String typeName, Integer direction) {
            this.id = id;
            this.startCode = startCode;
            this.startNameZh = startNameZh;
            this.endCode = endCode;
            this.endNameZh = endNameZh;
            this.weight = weight;
            this.typeCode = typeCode;
            this.typeName = typeName;
            this.direction = direction;
        }

        public Relationship(long id, Node start, Node end, Integer weight, ProductChainTypeEnum typeEnum, Integer direction) {
            this.id = id;
            this.startCode = start.getProductCode();
            this.startNameZh = start.getNameZh();
            this.startNameEn = start.getNameEn();
            this.endCode = end.getProductCode();
            this.endNameZh = end.getNameZh();
            this.endNameEn = end.getNameEn();
            this.weight = weight;
            this.direction = direction;
            if (typeEnum != null) {
                this.typeCode = typeEnum.name();
                this.typeName = typeEnum.getName();
            }

        }
    }


}
