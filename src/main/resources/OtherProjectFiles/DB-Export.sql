prompt PL/SQL Developer Export User Objects for user MCE@XE
prompt Created by Ali on پنجشنبه, 7 دسامبر 2023
set define off
spool DB-Export.log

prompt
prompt Creating table PERSON
prompt =====================
prompt
create table MCE.PERSON
(
  id                 NUMBER(20) not null,
  national_code      VARCHAR2(10) not null,
  name               VARCHAR2(200) not null,
  family             VARCHAR2(300) not null,
  father_name        VARCHAR2(200),
  birthdate          DATE,
  identifire_num     VARCHAR2(10),
  issuance_place     VARCHAR2(200),
  postal_code        VARCHAR2(10),
  home_address       VARCHAR2(500),
  mobile_number      VARCHAR2(10) not null,
  phone_number       VARCHAR2(10),
  email              VARCHAR2(250 CHAR),
  created_by         VARCHAR2(255 CHAR) not null,
  created_date       TIMESTAMP(6) not null,
  last_modified_by   VARCHAR2(255 CHAR) not null,
  last_modified_date TIMESTAMP(6) not null,
  effective_date     DATE not null,
  disable_date       DATE
)
tablespace SYSTEM
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on table MCE.PERSON
  is 'پرسنل شرکت';
comment on column MCE.PERSON.id
  is 'کلید اصلی جدول';
comment on column MCE.PERSON.national_code
  is 'کد ملی';
comment on column MCE.PERSON.name
  is 'نام';
comment on column MCE.PERSON.family
  is 'نام خانوادگی';
comment on column MCE.PERSON.father_name
  is 'نام پدر';
comment on column MCE.PERSON.birthdate
  is 'تاریخ تولد';
comment on column MCE.PERSON.identifire_num
  is 'شماره شناسنامه';
comment on column MCE.PERSON.issuance_place
  is 'محل صدور شناسنامه';
comment on column MCE.PERSON.postal_code
  is 'کد پستی
';
comment on column MCE.PERSON.home_address
  is 'آدرس محل زندگی
';
comment on column MCE.PERSON.mobile_number
  is 'شماره تماس همراه
';
comment on column MCE.PERSON.phone_number
  is 'شماره تماس ثابت
';
comment on column MCE.PERSON.email
  is 'آدرس پست الکترونیک';
alter table MCE.PERSON
  add constraint PERSN_PK primary key (ID)
  using index 
  tablespace SYSTEM
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table MCE.PERSON
  add constraint PERSN_1_UK unique (NATIONAL_CODE)
  using index 
  tablespace SYSTEM
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table MCE.PERSON
  add constraint PERSN_2_UK unique (MOBILE_NUMBER)
  using index 
  tablespace SYSTEM
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table STATE
prompt ====================
prompt
create table MCE.STATE
(
  id                 NUMBER(20) not null,
  name               VARCHAR2(100) not null,
  tel_code           VARCHAR2(3) not null,
  created_by         VARCHAR2(255 CHAR) not null,
  created_date       TIMESTAMP(6) not null,
  last_modified_by   VARCHAR2(255 CHAR) not null,
  last_modified_date TIMESTAMP(6) not null,
  effective_date     DATE not null,
  disable_date       DATE
)
tablespace SYSTEM
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on table MCE.STATE
  is 'استان ها';
comment on column MCE.STATE.id
  is 'کلید اصلی';
comment on column MCE.STATE.name
  is 'نام استان';
comment on column MCE.STATE.tel_code
  is 'کد تلفن استان';
alter table MCE.STATE
  add constraint STATE_PK primary key (ID)
  using index 
  tablespace SYSTEM
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table MCE.STATE
  add constraint STATE_UK unique (NAME)
  using index 
  tablespace SYSTEM
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table BRANCH
prompt =====================
prompt
create table MCE.BRANCH
(
  id                      NUMBER(20) not null,
  parent_id               NUMBER(20),
  code                    VARCHAR2(10 CHAR) not null,
  name                    VARCHAR2(100 CHAR) not null,
  state_id                NUMBER(20) not null,
  person_id               NUMBER(20) not null,
  mc_doc_print_permission NUMBER(1) not null,
  branch_typ              NUMBER(1) not null,
  email                   VARCHAR2(250 CHAR),
  created_by              VARCHAR2(255 CHAR) not null,
  created_date            TIMESTAMP(6) not null,
  last_modified_by        VARCHAR2(255 CHAR) not null,
  last_modified_date      TIMESTAMP(6) not null,
  effective_date          DATE not null,
  disable_date            DATE
)
tablespace SYSTEM
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on table MCE.BRANCH
  is 'نمایندگی های فروش و خدمات پس از فروش';
comment on column MCE.BRANCH.id
  is 'کلید اصلی';
comment on column MCE.BRANCH.parent_id
  is 'کلید خارجی - نمایندگی بالاسری';
comment on column MCE.BRANCH.code
  is 'کد یکتای نمایندگی';
comment on column MCE.BRANCH.name
  is 'نام نمایندگی';
comment on column MCE.BRANCH.state_id
  is 'کلید خارجی - استان حضور نمایندگی';
comment on column MCE.BRANCH.person_id
  is 'کلید خارجی - مالک نمایندگی';
comment on column MCE.BRANCH.mc_doc_print_permission
  is 'مجوز چاپ سند موتورسیکلت';
comment on column MCE.BRANCH.branch_typ
  is 'نوع نمایندگی: ۰=فروش ۱=خدمات پس از فروش';
comment on column MCE.BRANCH.email
  is 'آدرس پست الکترونیک';
alter table MCE.BRANCH
  add constraint BRNCH_PK primary key (ID)
  using index 
  tablespace SYSTEM
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table MCE.BRANCH
  add constraint BRNCH_UK unique (CODE)
  using index 
  tablespace SYSTEM
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table MCE.BRANCH
  add constraint BRNCH_FK foreign key (PARENT_ID)
  references MCE.BRANCH (ID);
alter table MCE.BRANCH
  add constraint BRNCH_PERSN_FK foreign key (PERSON_ID)
  references MCE.PERSON (ID);
alter table MCE.BRANCH
  add constraint BRNCH_STATE_FK foreign key (STATE_ID)
  references MCE.STATE (ID);

prompt
prompt Creating table CUSTOMER
prompt =======================
prompt
create table MCE.CUSTOMER
(
  id                 NUMBER(20) not null,
  national_code      VARCHAR2(10) not null,
  name               VARCHAR2(200) not null,
  family             VARCHAR2(300) not null,
  father_name        VARCHAR2(200),
  birthdate          DATE,
  identifire_num     VARCHAR2(10),
  issuance_place     VARCHAR2(200),
  postal_code        VARCHAR2(10),
  home_address       VARCHAR2(500),
  mobile_number      VARCHAR2(10) not null,
  phone_number       VARCHAR2(10),
  email              VARCHAR2(250 CHAR),
  created_by         VARCHAR2(255 CHAR) not null,
  created_date       TIMESTAMP(6) not null,
  last_modified_by   VARCHAR2(255 CHAR) not null,
  last_modified_date TIMESTAMP(6) not null,
  effective_date     DATE not null,
  disable_date       DATE
)
tablespace SYSTEM
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on table MCE.CUSTOMER
  is 'خریدار نهایی';
comment on column MCE.CUSTOMER.id
  is 'کلید اصلی';
comment on column MCE.CUSTOMER.national_code
  is 'کد ملی';
comment on column MCE.CUSTOMER.name
  is 'نام';
comment on column MCE.CUSTOMER.family
  is 'نام خانوادگی';
comment on column MCE.CUSTOMER.father_name
  is 'نام پدر';
comment on column MCE.CUSTOMER.birthdate
  is 'تاریخ تولد';
comment on column MCE.CUSTOMER.identifire_num
  is 'شماره شناسنامه';
comment on column MCE.CUSTOMER.issuance_place
  is 'محل صدور شناسنامه';
comment on column MCE.CUSTOMER.postal_code
  is 'کد پستی
';
comment on column MCE.CUSTOMER.home_address
  is 'آدرس محل زندگی
';
comment on column MCE.CUSTOMER.mobile_number
  is 'شماره تماس همراه
';
comment on column MCE.CUSTOMER.phone_number
  is 'شماره تماس ثابت
';
comment on column MCE.CUSTOMER.email
  is 'آدرس پست الکترونیک';
alter table MCE.CUSTOMER
  add constraint CUSTOMER_PK primary key (ID)
  using index 
  tablespace SYSTEM
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table MCE.CUSTOMER
  add constraint CUSTOMER_UK unique (NATIONAL_CODE)
  using index 
  tablespace SYSTEM
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table ATTACHMENT
prompt =========================
prompt
create table MCE.ATTACHMENT
(
  id                 NUMBER(20) not null,
  attach_file_1      BLOB not null,
  attach_desc_1      VARCHAR2(200),
  attach_file_2      BLOB,
  attach_desc_2      VARCHAR2(200),
  attach_file_3      BLOB,
  attach_desc_3      VARCHAR2(200),
  attach_file_4      BLOB,
  attach_desc_4      VARCHAR2(200),
  attach_file_5      BLOB,
  attach_desc_5      VARCHAR2(200),
  attach_file_6      BLOB,
  attach_desc_6      VARCHAR2(200),
  attach_file_7      BLOB,
  attach_desc_7      VARCHAR2(200),
  attach_file_8      BLOB,
  attach_desc_8      VARCHAR2(200),
  attach_file_9      BLOB,
  attach_desc_9      VARCHAR2(200),
  attach_file_10     BLOB,
  attach_desc_10     VARCHAR2(200),
  created_by         VARCHAR2(255 CHAR) not null,
  created_date       TIMESTAMP(6) not null,
  last_modified_by   VARCHAR2(255 CHAR) not null,
  last_modified_date TIMESTAMP(6) not null,
  effective_date     DATE not null,
  disable_date       DATE
)
tablespace SYSTEM
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on table MCE.ATTACHMENT
  is 'ضمائم';
comment on column MCE.ATTACHMENT.id
  is 'کلید اصلی ';
comment on column MCE.ATTACHMENT.attach_file_1
  is 'فایل ضمیمه';
comment on column MCE.ATTACHMENT.attach_desc_1
  is 'شرح فایل ضمیمه';
comment on column MCE.ATTACHMENT.attach_file_2
  is 'فایل ضمیمه';
comment on column MCE.ATTACHMENT.attach_desc_2
  is 'شرح فایل ضمیمه';
comment on column MCE.ATTACHMENT.attach_file_3
  is 'فایل ضمیمه';
comment on column MCE.ATTACHMENT.attach_desc_3
  is 'شرح فایل ضمیمه';
comment on column MCE.ATTACHMENT.attach_file_4
  is 'فایل ضمیمه';
comment on column MCE.ATTACHMENT.attach_desc_4
  is 'شرح فایل ضمیمه';
comment on column MCE.ATTACHMENT.attach_file_5
  is 'فایل ضمیمه';
comment on column MCE.ATTACHMENT.attach_desc_5
  is 'شرح فایل ضمیمه';
comment on column MCE.ATTACHMENT.attach_file_6
  is 'فایل ضمیمه';
comment on column MCE.ATTACHMENT.attach_desc_6
  is 'شرح فایل ضمیمه';
comment on column MCE.ATTACHMENT.attach_file_7
  is 'فایل ضمیمه';
comment on column MCE.ATTACHMENT.attach_desc_7
  is 'شرح فایل ضمیمه';
comment on column MCE.ATTACHMENT.attach_file_8
  is 'فایل ضمیمه';
comment on column MCE.ATTACHMENT.attach_desc_8
  is 'شرح فایل ضمیمه';
comment on column MCE.ATTACHMENT.attach_file_9
  is 'فایل ضمیمه';
comment on column MCE.ATTACHMENT.attach_desc_9
  is 'شرح فایل ضمیمه';
comment on column MCE.ATTACHMENT.attach_file_10
  is 'فایل ضمیمه';
comment on column MCE.ATTACHMENT.attach_desc_10
  is 'شرح فایل ضمیمه';
alter table MCE.ATTACHMENT
  add constraint ATTACH_PK primary key (ID)
  using index 
  tablespace SYSTEM
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table MOTORCYCLE_TYPE
prompt ==============================
prompt
create table MCE.MOTORCYCLE_TYPE
(
  id                    NUMBER(20) not null,
  information_attach_id NUMBER(20),
  code                  VARCHAR2(10 CHAR) not null,
  name                  VARCHAR2(100 CHAR) not null,
  created_by            VARCHAR2(255 CHAR) not null,
  created_date          TIMESTAMP(6) not null,
  last_modified_by      VARCHAR2(255 CHAR) not null,
  last_modified_date    TIMESTAMP(6) not null,
  effective_date        DATE not null,
  disable_date          DATE
)
tablespace SYSTEM
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on table MCE.MOTORCYCLE_TYPE
  is 'انواع موتورسیکلت‌ها';
comment on column MCE.MOTORCYCLE_TYPE.id
  is 'کیلد اصلی';
comment on column MCE.MOTORCYCLE_TYPE.information_attach_id
  is 'کلید خارجی - لینک اطلاعات فنی';
comment on column MCE.MOTORCYCLE_TYPE.code
  is 'کد';
comment on column MCE.MOTORCYCLE_TYPE.name
  is 'نام';
alter table MCE.MOTORCYCLE_TYPE
  add constraint MCT_PK primary key (ID)
  using index 
  tablespace SYSTEM
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table MCE.MOTORCYCLE_TYPE
  add constraint MCT_UK unique (INFORMATION_ATTACH_ID)
  using index 
  tablespace SYSTEM
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table MCE.MOTORCYCLE_TYPE
  add constraint MCT_ATTACH_FK foreign key (INFORMATION_ATTACH_ID)
  references MCE.ATTACHMENT (ID);

prompt
prompt Creating table STATUS
prompt =====================
prompt
create table MCE.STATUS
(
  id                 NUMBER(20) not null,
  status_flow        VARCHAR2(4) not null,
  order_num          NUMBER(3),
  code               VARCHAR2(5) not null,
  desc_persian       VARCHAR2(100),
  desc_english       VARCHAR2(10 CHAR),
  created_by         VARCHAR2(255 CHAR) not null,
  created_date       TIMESTAMP(6) not null,
  last_modified_by   VARCHAR2(255 CHAR) not null,
  last_modified_date TIMESTAMP(6) not null,
  effective_date     DATE not null,
  disable_date       DATE
)
tablespace SYSTEM
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on table MCE.STATUS
  is 'وضعیت های ممکن برای کلیه فرآیندها';
comment on column MCE.STATUS.id
  is 'کلید اصلی';
comment on column MCE.STATUS.status_flow
  is 'چهار حرفی مشخص کننده‌ی نام فرآیند برای تفکیک وضعیت‌ها از هم';
comment on column MCE.STATUS.order_num
  is 'ترتیب';
comment on column MCE.STATUS.code
  is 'کد یکتا در هر فرآیند';
comment on column MCE.STATUS.desc_persian
  is 'شرح فارسی';
comment on column MCE.STATUS.desc_english
  is 'شرح لاتین';
alter table MCE.STATUS
  add constraint STATUS_PK primary key (ID)
  using index 
  tablespace SYSTEM
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table MCE.STATUS
  add constraint STATUS_1_UK unique (STATUS_FLOW, CODE)
  using index 
  tablespace SYSTEM
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table MCE.STATUS
  add constraint STATUS_2_UK unique (STATUS_FLOW, ORDER_NUM)
  using index 
  tablespace SYSTEM
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table ENGINE_TYPE
prompt ==========================
prompt
create table MCE.ENGINE_TYPE
(
  id                 NUMBER(20) not null,
  code               VARCHAR2(20) not null,
  name               VARCHAR2(200),
  volume             NUMBER(4),
  power              NUMBER(4),
  fuel               NUMBER(1),
  created_by         VARCHAR2(255 CHAR) not null,
  created_date       TIMESTAMP(6) not null,
  last_modified_by   VARCHAR2(255 CHAR) not null,
  last_modified_date TIMESTAMP(6) not null,
  effective_date     DATE not null,
  disable_date       DATE
)
tablespace SYSTEM
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on table MCE.ENGINE_TYPE
  is 'انواع انجین های موتورسیکلت';
comment on column MCE.ENGINE_TYPE.id
  is 'کلید اصلی';
comment on column MCE.ENGINE_TYPE.code
  is 'کد';
comment on column MCE.ENGINE_TYPE.name
  is 'نام';
comment on column MCE.ENGINE_TYPE.volume
  is 'حجم موتور به سی سی';
comment on column MCE.ENGINE_TYPE.power
  is 'قدرت موتور به اسب بخار';
comment on column MCE.ENGINE_TYPE.fuel
  is 'سوخت: ۰=بنزین ۱=گازئیل ۲=الکتریکی';
alter table MCE.ENGINE_TYPE
  add constraint ENGTYP_PK primary key (ID)
  using index 
  tablespace SYSTEM
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table MCE.ENGINE_TYPE
  add constraint ENGTYP_UK unique (CODE)
  using index 
  tablespace SYSTEM
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table WAREHOUSE_INPUT
prompt ==============================
prompt
create table MCE.WAREHOUSE_INPUT
(
  id                    NUMBER(20) not null,
  engine_num_start      NUMBER(18) not null,
  engine_num_end        NUMBER(18) not null,
  input_date            DATE not null,
  control_date          DATE,
  prod_permission_date  DATE,
  receipt_number        VARCHAR2(10),
  controller_persn_id   NUMBER(20),
  identifiers_attach_id NUMBER(20),
  receipts_attach_id    NUMBER(20),
  reports_attach_id     NUMBER(20),
  prod_perm_persn_id    NUMBER(20),
  bom_link_url          VARCHAR2(400),
  year                  NUMBER(4) not null,
  engine_type_id        NUMBER(20) not null,
  kootaj_num            VARCHAR2(8) not null,
  num                   NUMBER(6) not null,
  created_by            VARCHAR2(255 CHAR) not null,
  created_date          TIMESTAMP(6) not null,
  last_modified_by      VARCHAR2(255 CHAR) not null,
  last_modified_date    TIMESTAMP(6) not null,
  effective_date        DATE not null,
  disable_date          DATE
)
tablespace SYSTEM
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on table MCE.WAREHOUSE_INPUT
  is 'موتور سیکلت های ورودی به انبار';
comment on column MCE.WAREHOUSE_INPUT.id
  is 'کلید اصلی';
comment on column MCE.WAREHOUSE_INPUT.engine_num_start
  is 'شروع شماره موتور';
comment on column MCE.WAREHOUSE_INPUT.engine_num_end
  is 'پایان شماره موتور';
comment on column MCE.WAREHOUSE_INPUT.input_date
  is 'تاریخ ورود به انبار';
comment on column MCE.WAREHOUSE_INPUT.control_date
  is 'تاریخ کنترل';
comment on column MCE.WAREHOUSE_INPUT.prod_permission_date
  is 'تاریخ صدور مجوز تولید';
comment on column MCE.WAREHOUSE_INPUT.receipt_number
  is 'شماره رسید';
comment on column MCE.WAREHOUSE_INPUT.controller_persn_id
  is 'کلید خارجی - شخص کنترل کننده';
comment on column MCE.WAREHOUSE_INPUT.identifiers_attach_id
  is 'کلید خارجی - لینک شناسنامه ها';
comment on column MCE.WAREHOUSE_INPUT.receipts_attach_id
  is 'کلید خارجی - لینک رسید به انبار';
comment on column MCE.WAREHOUSE_INPUT.reports_attach_id
  is 'کلید خارجی - لینک گزارش ورودی به انبار';
comment on column MCE.WAREHOUSE_INPUT.prod_perm_persn_id
  is 'کلید خارجی - شخص صادر کننده مجوز تولید';
comment on column MCE.WAREHOUSE_INPUT.bom_link_url
  is 'BOM لینک';
comment on column MCE.WAREHOUSE_INPUT.year
  is 'سال';
comment on column MCE.WAREHOUSE_INPUT.engine_type_id
  is 'کلید خارجی - نوع موتور';
comment on column MCE.WAREHOUSE_INPUT.kootaj_num
  is 'شماره کوتاژ';
comment on column MCE.WAREHOUSE_INPUT.num
  is 'تعداد';
alter table MCE.WAREHOUSE_INPUT
  add constraint WHIN_PK primary key (ID)
  using index 
  tablespace SYSTEM
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table MCE.WAREHOUSE_INPUT
  add constraint WHIN_1_UK unique (IDENTIFIERS_ATTACH_ID)
  using index 
  tablespace SYSTEM
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table MCE.WAREHOUSE_INPUT
  add constraint WHIN_2_UK unique (RECEIPTS_ATTACH_ID)
  using index 
  tablespace SYSTEM
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table MCE.WAREHOUSE_INPUT
  add constraint WHIN_3_UK unique (REPORTS_ATTACH_ID)
  using index 
  tablespace SYSTEM
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table MCE.WAREHOUSE_INPUT
  add constraint WHIN_ATTACH_1_FK foreign key (IDENTIFIERS_ATTACH_ID)
  references MCE.ATTACHMENT (ID);
alter table MCE.WAREHOUSE_INPUT
  add constraint WHIN_ATTACH_2_FK foreign key (RECEIPTS_ATTACH_ID)
  references MCE.ATTACHMENT (ID);
alter table MCE.WAREHOUSE_INPUT
  add constraint WHIN_ATTACH_3_FK foreign key (REPORTS_ATTACH_ID)
  references MCE.ATTACHMENT (ID);
alter table MCE.WAREHOUSE_INPUT
  add constraint WHIN_ENGTYP_FK foreign key (ENGINE_TYPE_ID)
  references MCE.ENGINE_TYPE (ID);
alter table MCE.WAREHOUSE_INPUT
  add constraint WHIN_PERSN_1_FK foreign key (CONTROLLER_PERSN_ID)
  references MCE.PERSON (ID);
alter table MCE.WAREHOUSE_INPUT
  add constraint WHIN_PERSN_2_FK foreign key (PROD_PERM_PERSN_ID)
  references MCE.PERSON (ID);

prompt
prompt Creating table COLOR
prompt ====================
prompt
create table MCE.COLOR
(
  id                 NUMBER(20) not null,
  code               VARCHAR2(10),
  persian_name       VARCHAR2(100) not null,
  english_name       VARCHAR2(100),
  r                  NUMBER(3),
  g                  NUMBER(3),
  b                  NUMBER(3),
  created_by         VARCHAR2(255 CHAR) not null,
  created_date       TIMESTAMP(6) not null,
  last_modified_by   VARCHAR2(255 CHAR) not null,
  last_modified_date TIMESTAMP(6) not null,
  effective_date     DATE not null,
  disable_date       DATE
)
tablespace SYSTEM
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on table MCE.COLOR
  is 'رنگ ها';
comment on column MCE.COLOR.id
  is 'کلید اصلی';
comment on column MCE.COLOR.code
  is 'کد رنگ';
comment on column MCE.COLOR.persian_name
  is 'نام فارسی';
comment on column MCE.COLOR.english_name
  is 'نام لاتین';
comment on column MCE.COLOR.r
  is 'RGB رنگ قرمز در فرمت';
comment on column MCE.COLOR.g
  is 'RGB رنگ سبز در فرمت';
comment on column MCE.COLOR.b
  is 'RGB رنگ آبی در فرمت';
alter table MCE.COLOR
  add constraint COLOR_PK primary key (ID)
  using index 
  tablespace SYSTEM
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table MCE.COLOR
  add constraint COLOR_1_UK unique (CODE)
  using index 
  tablespace SYSTEM
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table MCE.COLOR
  add constraint COLOR_2_UK unique (R, G, B)
  using index 
  tablespace SYSTEM
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table MCE.COLOR
  add constraint B
  check (B<= 255);
alter table MCE.COLOR
  add constraint G
  check (G <= 255);
alter table MCE.COLOR
  add constraint R
  check (R <= 255);

prompt
prompt Creating table MOTORCYCLE
prompt =========================
prompt
create table MCE.MOTORCYCLE
(
  id                 NUMBER(20) not null,
  mc_type_id         NUMBER(20) not null,
  branch_id          NUMBER(20),
  customer_id        NUMBER(20),
  wh_input_id        NUMBER(20) not null,
  engine_num         VARCHAR2(20) not null,
  chassis_num        VARCHAR2(20) not null,
  color_id           NUMBER(20) not null,
  status_id          NUMBER(20) not null,
  numbering_date     DATE,
  exit_date          DATE,
  license_plate      VARCHAR2(10),
  waybill_num        VARCHAR2(10),
  sub_branch_id      NUMBER(20),
  state_id           NUMBER(20),
  customer_buy_date  DATE,
  mc_docs_attach_id  NUMBER(20),
  created_by         VARCHAR2(255 CHAR) not null,
  created_date       TIMESTAMP(6) not null,
  last_modified_by   VARCHAR2(255 CHAR) not null,
  last_modified_date TIMESTAMP(6) not null,
  effective_date     DATE not null,
  disable_date       DATE
)
tablespace SYSTEM
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on table MCE.MOTORCYCLE
  is 'موتورسیکلیت های ساخته شده';
comment on column MCE.MOTORCYCLE.id
  is 'کلید اصلی';
comment on column MCE.MOTORCYCLE.mc_type_id
  is 'کلید خارجی - نوع موتورسیکلت';
comment on column MCE.MOTORCYCLE.branch_id
  is 'کلید خارجی - نمایندگی فروش اصلی';
comment on column MCE.MOTORCYCLE.customer_id
  is 'کلید خارجی - خریدار نهایی';
comment on column MCE.MOTORCYCLE.wh_input_id
  is 'کلید خارجی - کوتاژ موتورسیکلت ورودی به انبار';
comment on column MCE.MOTORCYCLE.engine_num
  is 'شماره یکتای موتور';
comment on column MCE.MOTORCYCLE.chassis_num
  is 'شماره یکتای شاسی';
comment on column MCE.MOTORCYCLE.color_id
  is 'کلید خارجی - رنگ';
comment on column MCE.MOTORCYCLE.status_id
  is 'کلید خارجی - وضعیت موتورسیکلت درفرآیند، تولید تا تحویل مشتری';
comment on column MCE.MOTORCYCLE.numbering_date
  is 'تاریخ شماره گذاری';
comment on column MCE.MOTORCYCLE.exit_date
  is 'تاریخ خروج';
comment on column MCE.MOTORCYCLE.license_plate
  is 'شماره پلاک';
comment on column MCE.MOTORCYCLE.waybill_num
  is 'شماره بارنامه';
comment on column MCE.MOTORCYCLE.sub_branch_id
  is 'کلید خارجی - زیر نمایندگی فروش';
comment on column MCE.MOTORCYCLE.state_id
  is 'کلید خارجی - استان تحویل شده';
comment on column MCE.MOTORCYCLE.customer_buy_date
  is 'تاریخ خرید توسط خریدار نهایی';
comment on column MCE.MOTORCYCLE.mc_docs_attach_id
  is 'کلید خارجی - لینک مدارک استانداردی
';
alter table MCE.MOTORCYCLE
  add constraint MC_PK primary key (ID)
  using index 
  tablespace SYSTEM
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table MCE.MOTORCYCLE
  add constraint MC_1_UK unique (ENGINE_NUM)
  using index 
  tablespace SYSTEM
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table MCE.MOTORCYCLE
  add constraint MC_2_UK unique (CHASSIS_NUM)
  using index 
  tablespace SYSTEM
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table MCE.MOTORCYCLE
  add constraint MC_3_UK unique (MC_DOCS_ATTACH_ID)
  using index 
  tablespace SYSTEM
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table MCE.MOTORCYCLE
  add constraint MC_ATTACH_FK foreign key (MC_DOCS_ATTACH_ID)
  references MCE.ATTACHMENT (ID);
alter table MCE.MOTORCYCLE
  add constraint MC_BRNCH_1_FK foreign key (BRANCH_ID)
  references MCE.BRANCH (ID);
alter table MCE.MOTORCYCLE
  add constraint MC_BRNCH_2_FK foreign key (SUB_BRANCH_ID)
  references MCE.BRANCH (ID);
alter table MCE.MOTORCYCLE
  add constraint MC_COLOR_FK foreign key (COLOR_ID)
  references MCE.COLOR (ID);
alter table MCE.MOTORCYCLE
  add constraint MC_CUSTOMER_FK foreign key (CUSTOMER_ID)
  references MCE.CUSTOMER (ID);
alter table MCE.MOTORCYCLE
  add constraint MC_MCT_FK foreign key (MC_TYPE_ID)
  references MCE.MOTORCYCLE_TYPE (ID);
alter table MCE.MOTORCYCLE
  add constraint MC_STATE_FK foreign key (STATE_ID)
  references MCE.STATE (ID);
alter table MCE.MOTORCYCLE
  add constraint MC_STATUS_FK foreign key (STATUS_ID)
  references MCE.STATUS (ID);
alter table MCE.MOTORCYCLE
  add constraint MC_WHIN_FK foreign key (WH_INPUT_ID)
  references MCE.WAREHOUSE_INPUT (ID);

prompt
prompt Creating table AFTERSALES_SERVICE
prompt =================================
prompt
create table MCE.AFTERSALES_SERVICE
(
  id                 NUMBER(20) not null,
  service_title      VARCHAR2(200) not null,
  service_desc       VARCHAR2(2000) not null,
  service_date       DATE not null,
  customer_id        NUMBER(20) not null,
  motorcycle_id      NUMBER(20) not null,
  branch_id          NUMBER(20) not null,
  kilometer          NUMBER(7) not null,
  created_by         VARCHAR2(255 CHAR) not null,
  created_date       TIMESTAMP(6) not null,
  last_modified_by   VARCHAR2(255 CHAR) not null,
  last_modified_date TIMESTAMP(6) not null,
  effective_date     DATE not null,
  disable_date       DATE
)
tablespace SYSTEM
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on table MCE.AFTERSALES_SERVICE
  is 'خدمات پس از فروش';
comment on column MCE.AFTERSALES_SERVICE.id
  is 'کلید اصلی';
comment on column MCE.AFTERSALES_SERVICE.service_title
  is 'عنوان خدمت داده شده';
comment on column MCE.AFTERSALES_SERVICE.service_desc
  is 'شرح خدمت داده شده';
comment on column MCE.AFTERSALES_SERVICE.service_date
  is 'تاریخ خدمت داده شده';
comment on column MCE.AFTERSALES_SERVICE.customer_id
  is 'کلید خارجی - مشتری مالک موتورسیکلت';
comment on column MCE.AFTERSALES_SERVICE.motorcycle_id
  is 'کلید خارجی - موتورسیکلت سرویس گرفته';
comment on column MCE.AFTERSALES_SERVICE.branch_id
  is 'کلید خارجی - نمایندگی سرویس دهنده';
comment on column MCE.AFTERSALES_SERVICE.kilometer
  is 'کیلومتر وسیله در زمان مراجعه';
alter table MCE.AFTERSALES_SERVICE
  add constraint SERV_PK primary key (ID)
  using index 
  tablespace SYSTEM
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table MCE.AFTERSALES_SERVICE
  add constraint SERV_BRNCH_FK foreign key (BRANCH_ID)
  references MCE.BRANCH (ID);
alter table MCE.AFTERSALES_SERVICE
  add constraint SERV_CUSTOMER_FK foreign key (CUSTOMER_ID)
  references MCE.CUSTOMER (ID);
alter table MCE.AFTERSALES_SERVICE
  add constraint SERV_MC_FK foreign key (MOTORCYCLE_ID)
  references MCE.MOTORCYCLE (ID);

prompt
prompt Creating table CUSTOMER_SUPPORT
prompt ===============================
prompt
create table MCE.CUSTOMER_SUPPORT
(
  id                  NUMBER(20) not null,
  parent_id           NUMBER(20),
  customer_criticism  VARCHAR2(2000 CHAR),
  customer_suggestion VARCHAR2(2000 CHAR),
  customer_complaint  VARCHAR2(2000 CHAR),
  response            VARCHAR2(2000 CHAR),
  customer_pol        VARCHAR2(2000 CHAR),
  created_by          VARCHAR2(255 CHAR) not null,
  created_date        TIMESTAMP(6) not null,
  last_modified_by    VARCHAR2(255 CHAR) not null,
  last_modified_date  TIMESTAMP(6) not null,
  effective_date      DATE not null,
  disable_date        DATE
)
tablespace SYSTEM
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on table MCE.CUSTOMER_SUPPORT
  is 'سامانه نظرسنجی از مشتریان';
comment on column MCE.CUSTOMER_SUPPORT.id
  is 'کلید اصلی';
comment on column MCE.CUSTOMER_SUPPORT.parent_id
  is 'کلید خارجی - پیگیری مجدد درخواست';
comment on column MCE.CUSTOMER_SUPPORT.customer_criticism
  is 'انتقاد مشترسی';
comment on column MCE.CUSTOMER_SUPPORT.customer_suggestion
  is 'پیشنهاد مشتری';
comment on column MCE.CUSTOMER_SUPPORT.customer_complaint
  is 'شکایت مشتری';
comment on column MCE.CUSTOMER_SUPPORT.response
  is 'پاسخ به انتقادات، شکایات و پیشنهادات';
comment on column MCE.CUSTOMER_SUPPORT.customer_pol
  is 'نظرسنجی از مشتری';
alter table MCE.CUSTOMER_SUPPORT
  add constraint CUSUP_PK primary key (ID)
  using index 
  tablespace SYSTEM
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table MCE.CUSTOMER_SUPPORT
  add constraint CUSUP_UK unique (PARENT_ID)
  using index 
  tablespace SYSTEM
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table MCE.CUSTOMER_SUPPORT
  add constraint CUSUP_FK foreign key (PARENT_ID)
  references MCE.CUSTOMER_SUPPORT (ID);

prompt
prompt Creating table FINAL_CONTROL
prompt ============================
prompt
create table MCE.FINAL_CONTROL
(
  id                 NUMBER(20) not null,
  person_id          NUMBER(20) not null,
  status_id          NUMBER(20) not null,
  failure_title      VARCHAR2(200),
  failure_desc       VARCHAR2(2000),
  corrective_action  VARCHAR2(2000),
  description        VARCHAR2(2000),
  motorcycle_id      NUMBER(20) not null,
  created_by         VARCHAR2(255 CHAR) not null,
  created_date       TIMESTAMP(6) not null,
  last_modified_by   VARCHAR2(255 CHAR) not null,
  last_modified_date TIMESTAMP(6) not null,
  effective_date     DATE not null,
  disable_date       DATE
)
tablespace SYSTEM
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on table MCE.FINAL_CONTROL
  is 'کنترل نهایی';
comment on column MCE.FINAL_CONTROL.id
  is 'کلید اصلی';
comment on column MCE.FINAL_CONTROL.person_id
  is 'کلید خارجی - فرد کنترل کننده';
comment on column MCE.FINAL_CONTROL.status_id
  is 'کلید خارجی - وضعیت کنترل نهایی';
comment on column MCE.FINAL_CONTROL.failure_title
  is 'عنوان خرابی';
comment on column MCE.FINAL_CONTROL.failure_desc
  is 'شرح خرابی';
comment on column MCE.FINAL_CONTROL.corrective_action
  is 'اقدام اصلاحی';
comment on column MCE.FINAL_CONTROL.description
  is 'توضیحات';
comment on column MCE.FINAL_CONTROL.motorcycle_id
  is 'کلید خارجی - موتورسیکلت کنترل شده';
alter table MCE.FINAL_CONTROL
  add constraint FC_PK primary key (ID)
  using index 
  tablespace SYSTEM
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table MCE.FINAL_CONTROL
  add constraint FC_UK unique (MOTORCYCLE_ID)
  using index 
  tablespace SYSTEM
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table MCE.FINAL_CONTROL
  add constraint FC_MC_FK foreign key (MOTORCYCLE_ID)
  references MCE.MOTORCYCLE (ID);
alter table MCE.FINAL_CONTROL
  add constraint FC_PERSN_FK foreign key (PERSON_ID)
  references MCE.PERSON (ID);
alter table MCE.FINAL_CONTROL
  add constraint FC_STATUS_FK foreign key (STATUS_ID)
  references MCE.STATUS (ID);

prompt
prompt Creating table PRE_DELIVERY_CONTROL
prompt ===================================
prompt
create table MCE.PRE_DELIVERY_CONTROL
(
  id                 NUMBER(20) not null,
  person_id          NUMBER(20) not null,
  status_id          NUMBER(20) not null,
  failure_desc       VARCHAR2(2000),
  corrective_action  VARCHAR2(2000),
  description        VARCHAR2(2000),
  motorcycle_id      NUMBER(20) not null,
  created_by         VARCHAR2(255 CHAR) not null,
  created_date       TIMESTAMP(6) not null,
  last_modified_by   VARCHAR2(255 CHAR) not null,
  last_modified_date TIMESTAMP(6) not null,
  effective_date     DATE not null,
  disable_date       DATE
)
tablespace SYSTEM
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on table MCE.PRE_DELIVERY_CONTROL
  is 'اطلاعات کنترل قبل تحویل به نمایندگی';
comment on column MCE.PRE_DELIVERY_CONTROL.person_id
  is 'کلید خارجی - فرد کنترل کننده';
comment on column MCE.PRE_DELIVERY_CONTROL.status_id
  is 'کلید خارجی - وضعیت کنترل قبل از تحویل';
comment on column MCE.PRE_DELIVERY_CONTROL.failure_desc
  is 'شرح خرابی';
comment on column MCE.PRE_DELIVERY_CONTROL.corrective_action
  is 'اقدام اصلاحی';
comment on column MCE.PRE_DELIVERY_CONTROL.description
  is 'توضیحات';
comment on column MCE.PRE_DELIVERY_CONTROL.motorcycle_id
  is 'کلید خارجی - موتورسیکلت کنترل شده';
alter table MCE.PRE_DELIVERY_CONTROL
  add constraint PDC_PK primary key (ID)
  using index 
  tablespace SYSTEM
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table MCE.PRE_DELIVERY_CONTROL
  add constraint PDC unique (MOTORCYCLE_ID)
  using index 
  tablespace SYSTEM
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table MCE.PRE_DELIVERY_CONTROL
  add constraint PDC_MC_FK foreign key (MOTORCYCLE_ID)
  references MCE.MOTORCYCLE (ID);
alter table MCE.PRE_DELIVERY_CONTROL
  add constraint PDC_PERSN_FK foreign key (PERSON_ID)
  references MCE.PERSON (ID);
alter table MCE.PRE_DELIVERY_CONTROL
  add constraint PDC_STATUS_FK foreign key (STATUS_ID)
  references MCE.STATUS (ID);

prompt
prompt Creating table QUALITY_CONTROL
prompt ==============================
prompt
create table MCE.QUALITY_CONTROL
(
  id                 NUMBER(20) not null,
  qc_1_person_id     NUMBER(20) not null,
  qc_1_status_id     NUMBER(20) not null,
  qc_1_comment       VARCHAR2(2000) not null,
  qc_2_person_id     NUMBER(20),
  qc_2_status_id     NUMBER(20),
  qc_2_comment       VARCHAR2(2000),
  qc_3_person_id     NUMBER(20),
  qc_3_status_id     NUMBER(20),
  qc_3_comment       VARCHAR2(2000),
  motorcycle_id      NUMBER(20) not null,
  created_by         VARCHAR2(255 CHAR) not null,
  created_date       TIMESTAMP(6) not null,
  last_modified_by   VARCHAR2(255 CHAR) not null,
  last_modified_date TIMESTAMP(6) not null,
  effective_date     DATE not null,
  disable_date       DATE
)
tablespace SYSTEM
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on table MCE.QUALITY_CONTROL
  is 'گام های فرآیند کنترل کیفیت';
comment on column MCE.QUALITY_CONTROL.id
  is 'کلید اصلی';
comment on column MCE.QUALITY_CONTROL.qc_1_person_id
  is 'QC1 نام اپراتور';
comment on column MCE.QUALITY_CONTROL.qc_1_status_id
  is 'QC1 وضعیت';
comment on column MCE.QUALITY_CONTROL.qc_1_comment
  is 'QC1 توضیحات';
comment on column MCE.QUALITY_CONTROL.qc_2_person_id
  is 'QC2 نام اپراتور';
comment on column MCE.QUALITY_CONTROL.qc_2_status_id
  is 'QC2 وضعیت';
comment on column MCE.QUALITY_CONTROL.qc_2_comment
  is 'QC2 توضیحات';
comment on column MCE.QUALITY_CONTROL.qc_3_person_id
  is 'QC3 نام اپراتور';
comment on column MCE.QUALITY_CONTROL.qc_3_status_id
  is 'QC3 وضعیت';
comment on column MCE.QUALITY_CONTROL.qc_3_comment
  is 'QC3 توضیحات';
comment on column MCE.QUALITY_CONTROL.motorcycle_id
  is 'کلید خارجی - موتورسیکلت کنترل شده';
alter table MCE.QUALITY_CONTROL
  add constraint QC_PK primary key (ID)
  using index 
  tablespace SYSTEM
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table MCE.QUALITY_CONTROL
  add constraint QC_UK unique (MOTORCYCLE_ID)
  using index 
  tablespace SYSTEM
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table MCE.QUALITY_CONTROL
  add constraint QC_MC_FK foreign key (MOTORCYCLE_ID)
  references MCE.MOTORCYCLE (ID);
alter table MCE.QUALITY_CONTROL
  add constraint QC_PERSN_1_FK foreign key (QC_1_PERSON_ID)
  references MCE.PERSON (ID);
alter table MCE.QUALITY_CONTROL
  add constraint QC_PERSN_2_FK foreign key (QC_2_PERSON_ID)
  references MCE.PERSON (ID);
alter table MCE.QUALITY_CONTROL
  add constraint QC_PERSN_3_FK foreign key (QC_3_PERSON_ID)
  references MCE.PERSON (ID);
alter table MCE.QUALITY_CONTROL
  add constraint QC_STATUS_1_FK foreign key (QC_1_STATUS_ID)
  references MCE.STATUS (ID);
alter table MCE.QUALITY_CONTROL
  add constraint QC_STATUS_2_FK foreign key (QC_2_STATUS_ID)
  references MCE.STATUS (ID);
alter table MCE.QUALITY_CONTROL
  add constraint QC_STATUS_3_FK foreign key (QC_3_STATUS_ID)
  references MCE.STATUS (ID);

prompt
prompt Creating sequence SQ_AFTERSALES_SERVICE
prompt =======================================
prompt
create sequence MCE.SQ_AFTERSALES_SERVICE
minvalue 1
maxvalue 99999999999999999999
start with 1
increment by 1
cache 20;

prompt
prompt Creating sequence SQ_ATTACHMENT
prompt ===============================
prompt
create sequence MCE.SQ_ATTACHMENT
minvalue 1
maxvalue 99999999999999999999
start with 1
increment by 1
cache 20;

prompt
prompt Creating sequence SQ_BRANCH
prompt ===========================
prompt
create sequence MCE.SQ_BRANCH
minvalue 1
maxvalue 99999999999999999999
start with 61
increment by 1
cache 20;

prompt
prompt Creating sequence SQ_COLOR
prompt ==========================
prompt
create sequence MCE.SQ_COLOR
minvalue 1
maxvalue 99999999999999999999
start with 41
increment by 1
cache 20;

prompt
prompt Creating sequence SQ_CUSTOMER
prompt =============================
prompt
create sequence MCE.SQ_CUSTOMER
minvalue 1
maxvalue 99999999999999999999
start with 1
increment by 1
cache 20;

prompt
prompt Creating sequence SQ_CUSTOMER_SUPPORT
prompt =====================================
prompt
create sequence MCE.SQ_CUSTOMER_SUPPORT
minvalue 1
maxvalue 99999999999999999999
start with 1
increment by 1
cache 20;

prompt
prompt Creating sequence SQ_ENGINE_TYPE
prompt ================================
prompt
create sequence MCE.SQ_ENGINE_TYPE
minvalue 1
maxvalue 99999999999999999999
start with 1
increment by 1
cache 20;

prompt
prompt Creating sequence SQ_FINAL_CONTROL
prompt ==================================
prompt
create sequence MCE.SQ_FINAL_CONTROL
minvalue 1
maxvalue 99999999999999999999
start with 1
increment by 1
cache 20;

prompt
prompt Creating sequence SQ_MOTORCYCLE
prompt ===============================
prompt
create sequence MCE.SQ_MOTORCYCLE
minvalue 1
maxvalue 99999999999999999999
start with 1
increment by 1
cache 20;

prompt
prompt Creating sequence SQ_MOTORCYCLE_TYPE
prompt ====================================
prompt
create sequence MCE.SQ_MOTORCYCLE_TYPE
minvalue 1
maxvalue 999999999999999999999999999
start with 1
increment by 1
cache 20;

prompt
prompt Creating sequence SQ_PERSON
prompt ===========================
prompt
create sequence MCE.SQ_PERSON
minvalue 1
maxvalue 99999999999999999999
start with 1
increment by 1
cache 20;

prompt
prompt Creating sequence SQ_PRE_DELIVERY_CONTROL
prompt =========================================
prompt
create sequence MCE.SQ_PRE_DELIVERY_CONTROL
minvalue 1
maxvalue 99999999999999999999
start with 1
increment by 1
cache 20;

prompt
prompt Creating sequence SQ_QUALITY_CONTROL
prompt ====================================
prompt
create sequence MCE.SQ_QUALITY_CONTROL
minvalue 1
maxvalue 99999999999999999999
start with 1
increment by 1
cache 20;

prompt
prompt Creating sequence SQ_STATE
prompt ==========================
prompt
create sequence MCE.SQ_STATE
minvalue 1
maxvalue 99999999999999999999
start with 101
increment by 1
cache 20;

prompt
prompt Creating sequence SQ_STATUS
prompt ===========================
prompt
create sequence MCE.SQ_STATUS
minvalue 1
maxvalue 99999999999999999999
start with 1
increment by 1
cache 20;

prompt
prompt Creating sequence SQ_WAREHOUSE_INPUT
prompt ====================================
prompt
create sequence MCE.SQ_WAREHOUSE_INPUT
minvalue 1
maxvalue 99999999999999999999
start with 1
increment by 1
cache 20;


prompt Done
spool off
set define on
