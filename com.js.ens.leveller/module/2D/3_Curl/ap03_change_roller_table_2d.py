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

def change_lcase_time(tot_mx) :
	p_v = py_get_float("plate_v")
	ltime = py_get_float("lcase_par(,time) ")
	ltime_new = ltime + float(tot_mx/p_v)
	py_send("*loadcase_value time %f "%ltime_new)
	# print "Changed Loadcase time ",ltime_new,ltime,p_v,tot_mx 
	   
def move_plate(var) :
   fdist_hd = py_get_float("fdist")
   rdist_hd = py_get_float("rdist")  
   hdr_loc=var[2]
   roll_no=var[0]+var[1]
   
   if (hdr_loc == "none") or (hdr_loc == "None") :
       print "HD Roll is not created "
   else :	
       fhd_cx=py_get_float("cbody_par(front_hd_roll,cx)")
       rhd_cx=py_get_float("cbody_par(rear_hd_roll,cx)")
       print "HD Roll Info. : ", hdr_loc,fhd_cx,rhd_cx
       fhd_mx=fhd_cx-fdist_hd
       print " Front Roller Table & Plate Movement ",fhd_mx
       py_send("*move_reset *set_move_translation x %f " %fhd_mx) 
       py_send("*select_clear *select_method_single ")
       s1,s2,s3,s4=roll_no+1,roll_no+2,roll_no+3,roll_no+4
       print "Roller Table curves Id ",s1,s2,s3,s4
       py_send("*select_curves "+str(s1)+" "+str(s2)+"  #")
       py_send("*select_contact_body plate ")
       py_send("*move_elements all_selected *move_curves all_selected ")
       s_id=roll_no+3
       p_id=py_get_int("curve_point_id(%d,1)" %s_id )
       sx=py_get_float("point_x(%d)" %p_id)
       print "Point coordinate ", s_id, p_id,sx
       rhd_mx=rhd_cx-sx+rdist_hd       
       print " Rear Roller Table Movement ",rhd_mx
       py_send("*move_reset *set_move_translation x %f " %rhd_mx) 
       py_send("*select_clear *select_curves "+str(s3)+" "+str(s4) +"  #")
       py_send("*move_curves all_selected ")
       tot_mx = abs(fhd_mx) + abs(rhd_mx)
       change_lcase_time(tot_mx) 
     
def main():
   var=get_hd_info() 
   print "Hold Down Rolls variables ",var
   move_plate(var)
   py_send("*select_clear ")

     
if __name__ == '__main__':
    main()
