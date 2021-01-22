package com.csf.basedata.sammgt.common;


/**
 * @author michelle.min
 */
public class SamMgtConstants {
    private SamMgtConstants() {

    }

    public static final Integer UP_DIRECTION = -1;
    public static final Integer DOWN_DIRECTION = 1;
    public static final Integer DEFAULT_VERSION = 1;
    public static final String DEFAULT_USER = "SYSTEM";
    public static final Integer DEFAULT_STATUS = 1;
    public static final String CSFPRODUCTS = "CSFPRODUCTS";
    public static final String AUDIT_TYPE_PRODUCT = "product";
    public static final String AUDIT_TYPE_PRODUCT_CHAIN = "product_chain";
    public static final String AUDIT_TYPE_PRODUCT_TREE = "product_tree";
    public static final String CSFPRODUCT_CODE_REGEX = "\\w+";
    public static final String PRODUCTS_CSV = "products.csv";
    public static final String MAPPINGS_CSV = "mappings.csv";
    public static final String STREAMS_CSV = "streams.csv";

//    Init Message
    public static final String INIT_CHECK_DATA_EXIST = "Check Exist Data. Exist Product Count : %s, MappingCount : %s, StreamCount : %s, ProductInitCount : %s, ProductTreeCount: %s";
    public static final String INIT_DELETE_EXIST_DATA = "Delete Old Data. Delete Product Count : %s, MappingCount : %s, StreamCount : %s, ProductInitCount : %s, ProductTreeCount: %s";
    public static final String INIT_BULK_INSERT_DATA = "Bulk Insert Success. ProductCount : %s, MappingCount : %s, StreamCount : %s";
    public static final String INIT_CSV_FILE_EXIST = "CSV Files Check. Products.csv : %s, Mappings.csv : %s, Streams.csv : %s";
    public static final String INIT_RUNNING = "Initializing...Please Wait.";

    public static final String PRODUCT_CODE_SUFFIXES = "product_code_suffixes";
    public static final String PRODUCT_CODE_SUFFIXES_LOCK = "product_code_suffixes_lock";
    public static final String PRODUCT_CODE_INIT_SUFFIXES_LOCK = "product_code_init_suffixes_lock";
    public static final String PRODUCT_CODE_UPDATE_LEAF_LOCK = "product_code_update_leaf_lock";


}
