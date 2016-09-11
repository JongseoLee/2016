#
# This Routine is for vertical down scaling vertical position at the dummy plate boundary
# It will be used in remeshing step
#
from py_mentat import *
from py_post import *
# from sets import Set


def get_fname():  # get file name
   fname = py_get_string("model_name()")
   print "File names ==",fname
   return fname

def get_passline_info() :
#  
#  pvar[0] : pass_line_adj      : initial position of plate : mm :                   
   pvar=[]
   pvar.append(py_get_float("pass_line_adj"))            
   pvar.append(py_get_float("p_thk"))               
   print "Passline Information ",pvar
   
   return pvar    



def get_miny_nodes() :
   nodes=py_get_int("nnodes()")
   miny=-1.0e-20
   for i in range(1,nodes+1):
     # cx=py_get_float("node_x(%d)" %path[i])  
     cy=py_get_float("node_y(%d)" %i)  
     # cz=py_get_float("node_z(%d)" %path[i])
     if miny > cy :
     	  miny=cy        
   print "Minimum Value of dplate Y Coordinate is ",miny  
   return miny
   

def move_plate(move_y) :
	py_send("*move_reset ")
	py_send("*move_elements ")
	py_send("*set_move_translation y %f " %move_y ) 
	py_send("*move_elements all_existing ")
	py_send("*move_reset ")


            
def main():   
#
#  This Code is for adjusting dplate position and model section of plate 
#
   pvar=get_passline_info()
# Get minimum Y coordinate of dplate
   miny=get_miny_nodes()
# Move dplate & model section
   move_y = pvar[0] - pvar[1]/2.0 - miny 
   print " Move the plate as ",move_y
   py_send("*define msect_mv_y %f " %move_y )
   move_plate(move_y)

   return     

   
if __name__ == '__main__':
    py_connect("",40007)
    main()
      
    py_disconnect()






