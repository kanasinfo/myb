alter table QUSTNR_GROUP rename QUESTION_GROUP;
alter table QUESTION_GROUP add qustnr_templt_id varchar(32) not null;
alter table QUESTION_GROUP add active_flag bit;
alter table QUESTION_GROUP add norm_calc_val float DEFAULT NULL;
alter table QUESTION_GROUP add norm_input_val float DEFAULT NULL;
alter table QUESTION_GROUP add special_falg bit NULL;
alter table QUESTION add top_flag bit;