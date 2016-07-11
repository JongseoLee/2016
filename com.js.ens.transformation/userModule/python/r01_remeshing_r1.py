#
#
from py_mentat import *
from py_post import *


def get_fname():  # get file name
   fname = py_get_string("model_name()")
   print "File names ==",fname
   return fname
   
def claen_model() :
   py_send("*select_clear")
   py_send("*select_contact_body 01_Plate")
   py_send("*remove_elements")
   py_send("all_unselected")
   py_send("*remove_unused_nodes *clear_geometry")
   py_send("*grid_fix_v")
   py_send("*grid_u_domain -500 500")
   py_send("*grid_u_spacing 100")
   py_send("*grid_w_domain -1500 1500")
   py_send("*grid_w_spacing 100")
   py_send("*set_grid on *grid_style_lines")
   py_send("*show_view 2 *fill_view") 
   py_send("*select_clear  *renumber_all")
   return 

def find_max_min_x() :
   nunodes = py_get_int("nnodes()")  
   print "Number of Nodes    ==",nunodes
   nelems = py_get_int("nelements()")
   print "Number of Elements ==",nelems
   max_x,min_x=-1000000.0,1000000.0
   max_y,min_y=-1000000.0,1000000.0
   max_z,min_z=-1000000.0,1000000.0      
   i=1
   while i <= nunodes :
     n_id= py_get_int("node_id(%i)"%i)
     c_x = py_get_float("node_x(%i)"%n_id)
     c_y = py_get_float("node_y(%i)"%n_id)
     c_z = py_get_float("node_z(%i)"%n_id)
#     print n_id," :  ",c_x,c_y,c_z
     if (c_x > max_x) : 
        max_x = c_x  
     if (c_x < min_x) : 
        min_x = c_x  
     if (c_y > max_y) : 
        max_y = c_y  
     if (c_y < min_y) : 
        min_y = c_y  
     if (c_z > max_z) : 
        max_z = c_z  
     if (c_z < min_z) : 
        min_z = c_z  
     i=i+1
   print " Max. X :  ",max_x," Min. X :  ",min_x     
   print " Max. Y :  ",max_y," Min. Y :  ",min_y
   print " Max. Z :  ",max_z," Min. Z :  ",min_z
   cx,cy,cz=str((max_x+min_x)/2),str((max_y+min_y)/2),str((max_z+min_z)/2)
   py_send("*origin "+cx+" "+cy+" "+cz)
   return max_x,min_x,max_y,min_y,max_z,min_z  

def find_corner_node(max_x,min_x,max_y,min_y,max_z,min_z) :
   nunodes = py_get_int("nnodes()")  
   print "Number of Nodes    ==",nunodes
   nelems = py_get_int("nelements()")
   print "Number of Elements ==",nelems
   d1,d2,d3,d4,d5,d6,d7,d8 = 1.0e20,1.0e20,1.0e20,1.0e20,1.0e20,1.0e20,1.0e20,1.0e20
   i=1
   while i <= nunodes :
     n_id= py_get_int("node_id(%i)"%i)
     c_x = py_get_float("node_x(%i)"%n_id)
     c_y = py_get_float("node_y(%i)"%n_id)
     c_z = py_get_float("node_z(%i)"%n_id)
     d=((min_x-c_x)**2+(min_y-c_y)**2+(max_z-c_z)**2)**0.5
     if (d < d1) : 
        n1 = n_id
        d1 = d 
     i=i+1
   print " Corner Node 1 ",n1,d1
   return 

def find_node_set_id(name):
  n = py_get_int("nsets()")
  for i in range(1,n+1):
    id = py_get_int("set_id(%d)" % i)
    sname = py_get_string("set_name(%d)" %id)
    if( sname == name):
          return id 
  return -1
  
def get_node_id() :
    n_surf=[]
    name="surf_nodes"
    id=find_node_set_id(name)
    if  id >= 0 :
#     print name[n],id
      node_no=py_get_int("nset_entries(%d)" %id)
      for i in range(1,node_no+1) :  
        node_id=py_get_int("set_entry(%d,%d)" %(id,i))
        n_surf.append(node_id)
#        print "set no",id,node_no,i,node_id
    return n_surf
    
def find_surface_nodes() :
   py_send("*store_nodes surf_nodes all_surface")     
   n_surf=get_node_id()
   return n_surf
   
def remove_ext_elem() :
   py_send("*select_clear")
   py_send("*select_method_user_box")   
   py_send("*pick_inside_partial ")   
   py_send("*select_elements -500 500 -1000 1000 -10000 10000")      
   py_send("*remove_elements")
   py_send("all_unselected")
   py_send("*remove_unused_nodes ")
   py_send("*show_view 2 *fill_view") 
   py_send("*select_clear  *renumber_all")
   py_send("*select_method_single")
   py_send("*select_clear")      

               
def main():   
   py_send("*restore_model")     
   fname = get_fname()
   claen_model()
   max_x,min_x,max_y,min_y,max_z,min_z=find_max_min_x()
   remove_ext_elem()
   max_x,min_x,max_y,min_y,max_z,min_z=find_max_min_x()
#   find_corner_node(max_x,min_x,max_y,min_y,max_z,min_z)
   n_surf=find_surface_nodes()
   print n_surf,len(n_surf)
   py_send("*system_reset")     
   return     

   
if __name__ == '__main__':
    py_connect("",40007)
    main()
  
    py_disconnect()






