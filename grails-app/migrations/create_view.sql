create or replace view view_hi_procinst as 
select act_hi_procinst.*, var1.text2_ as Status, var2.text2_ as order_id 
from act_hi_procinst 
  left join (select * from act_hi_varinst where text_ = 'com.capgemini.capactiviti.model.ProcessStatus') as var1 on act_hi_procinst.PROC_INST_ID_ = var1.PROC_INST_ID_
  left join (select * from act_hi_varinst where text_ = 'com.capgemini.capactiviti.model.Order') as var2 on act_hi_procinst.PROC_INST_ID_ = var2.PROC_INST_ID_ 
