from py_mentat import *
# from py_post import *

def get_hd_info() :
   var=[]
   u_roll_no = py_get_int("u_roll_no")
   var.append(u_roll_no)
   l_roll_no = py_get_int("l_roll_no")
   var.append(l_roll_no)
   hd_roll_loc= py_get_string("hd_roll_loc")
   var.append(hd_roll_loc)
   fhd_dia = py_get_float("fhd_dia")
   var.append(fhd_dia)
   fhd_pitch = py_get_float("fhd_pitch")
   var.append(fhd_pitch)
   fhd_pos = py_get_float("fhd_pos")
   var.append(fhd_pos)
   rhd_dia = py_get_float("rhd_dia")
   var.append(rhd_dia)
   rhd_pitch = py_get_float("rhd_pitch")
   var.append(rhd_pitch)
   rhd_pos = py_get_float("rhd_pos")
   var.append(rhd_pos)
   return var


def get_motion_info() :
   mvar=[]
   URM_ent = py_get_float("URM_ent")
   URM_exit = py_get_float("URM_exit")
   URM_ts = py_get_float("URM_ts")
   URM_te = py_get_float("URM_te")
   LRM_ent = py_get_float("LRM_ent")
   LRM_exit = py_get_float("LRM_exit")
   LRM_ts = py_get_float("LRM_ts")
   LRM_te = py_get_float("LRM_te")
   mvar.append(URM_ent)
   mvar.append(URM_exit)
   mvar.append(URM_ts)
   mvar.append(URM_te)      
   mvar.append(LRM_ent)
   mvar.append(LRM_exit)
   mvar.append(LRM_ts)
   mvar.append(LRM_te)      
   return mvar
   
def create_motion_table(mvar) :
#   print  "check in motion table creating ",mvar
   py_send("*new_md_table 1 1 ")
   py_send("*table_name urmotion ")
   py_send("*set_md_table_type 1 time ")
   py_send("*table_add ")
   py_send("0  0 ")
   py_send("%s 0 " %mvar[2])
   py_send("%s+%s 1 " %(mvar[2],mvar[3]))
   py_send("%s+%s+1.0 1 " %(mvar[2],mvar[3]))
   py_send("*new_md_table 1 1 ")
   py_send("*table_name lrmotion ")
   py_send("*set_md_table_type 1 time ")
   py_send("*table_add ")
   py_send("0  0 ")
   py_send("%s 0 " %mvar[6])
   py_send("%s+%s 1 " %(mvar[6],mvar[7]))
   py_send("%s+%s+1.0 1 " %(mvar[6],mvar[7]))



def add_motion(tname,val) :
    py_send("*cbody_param_table py %s " %tname)
    py_send("*contact_value py %s " %val)


def add_contact(name,px,py,pz,tname,m_val) :
    py_send("*new_cbody geometry *contact_option geometry_nodes:off ")
    py_send("*contact_body_name %s " %name)
    py_send("*add_contact_body_curves %s " %(name+"_c"))  # in 2d
    py_send("*contact_option control:position")
    py_send("*cbody_center_rot %d %d %d " %(px,py,pz))
    py_send("*cbody_param_table py %s " %tname )
    py_send("*contact_value py %s " %m_val )
    py_send("*edit_contact_table ctable1 ")
    py_send("*ctable_entry plate %s " %name)
    py_send("*contact_table_option plate %s contact:on " %name)
    py_send("*prog_string ctable:old_interact no_fric *ctable_entry_interact plate %s " %name )

def hd_roll_gen_2d(rname,Dia,pitch,pos,fr_flag,l_flag,mvar) :
   cx=py_get_float("cbody_par(%s,cx)" %rname)
   cy=py_get_float("cbody_par(%s,cy)" %rname)
   cz=py_get_float("cbody_par(%s,cz)" %rname)   
   print cx,cy,cz
   px= cx - pitch 
#  fr_flag = 0 : front
#  fr_flag = 1 : rear 
#  l_flag = 0 : Upper
#  l_flag = 1 : Lower 
   if fr_flag == 0 :
      hdr_name = "front_hd_roll"
   elif fr_flag == 1 :   
      hdr_name = "rear_hd_roll"      
   else :
      print " Error to define the Horizontal location of Hold Down Roll "     
   if l_flag == 0 :
      py= cy - 280.0/2 + Dia/2 + pos
   elif l_flag == 1 :   
      py= cy + 280.0/2 - Dia/2 + pos
   else :
      print " Error to define the vertical location of Hold Down Roll "   
   pz= 0.0   # in 2D
   print px,py,pz
   py_send("*select_clear *visible_selected")   
   py_send("*set_curve_type circle_cr *add_curves ")
   py_send("*add_curves ")   
   py_send(" %d %d %d " % (px,py,0.0))
   py_send(" %d " % (Dia/2))
   # 2d case : py_send("*expand_reset ") 
   # 2d case : py_send("*set_expand_translation z roll_length+1000 ")
   # 2d case : py_send("*expand_curves all_visible ")
   py_send("*store_curves %s all_visible " %(hdr_name+"_c")) # chenaged in 2d
   
#    add contact   
   if (fr_flag == 0 and l_flag == 0) : 
       m_val = mvar[0]
       tname="urmotion"
   elif (fr_flag == 1 and l_flag == 0) : 
       m_val = mvar[1]
       tname="urmotion"   
   if (fr_flag == 0 and l_flag == 1) : 
       m_val = mvar[4]
       tname="lrmotion"
   elif (fr_flag == 1 and l_flag == 1) : 
       m_val = mvar[5]
       tname="lrmotion"                          
   add_contact(hdr_name,px,py,pz,tname,m_val)
   py_send("*select_clear *invisible_selected ")
   
def create_hd_roll(var,mvar) :
#  Create Table for Motion of Rolls
   rname=""
   hd_loc= var[2]
   hd_loc=hd_loc.strip()
   if hd_loc =="none" :
      print " Hold Down Roll is not created "
   if hd_loc =="upper" :
      print " Hold Down Roll is created at Upper "
      rname="UpperRoll" 
      rnamef = rname+"_1"      
      l_flag=0
      hd_roll_gen_2d(rnamef,var[3],var[4],var[5],0,l_flag,mvar)
      rnamer = rname+"_"+str(var[0])
      hd_roll_gen_2d(rnamer,var[6],-var[7],var[8],1,l_flag,mvar)
   elif hd_loc =="lower" :
      print " Hold Down Roll is created at lower "
      rname="LowerRoll"   
      rnamef = rname+"_1"
      l_flag=1 
      hd_roll_gen_2d(rnamef,var[3],var[4],var[5],0,l_flag,mvar)            
      rnamer = rname+"_"+str(var[1]) 
      hd_roll_gen_2d(rnamer,var[6],-var[7],var[8],1,l_flag,mvar)           
   else :
      print " ERROR to define Upper & Lower " 
   
def main():
   var=get_hd_info() 
   print "Hold Down Rolls variables ",var
   mvar=get_motion_info()
   print "Motion Variables ",mvar
   create_motion_table(mvar)
   create_hd_roll(var,mvar)

if __name__ == '__main__':
    main()
