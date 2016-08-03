from py_mentat import *
# from py_post import *


def save_set(setname,x,y,z) : 
   py_send("*remove_sets ",setname)
   py_send("*select_clear")
   py_send("*select_method_point_dist")
   tol=py_get_float("T")/50
#   print "Tolerance of finding nearest point ", tol
   py_send("*set_select_distance "+str(tol)) 
#   biased_x=tol/15 # it is for avoiding no search when half location
   py_send("*select_nodes " + str(x)+","+str(y)+","+str(z))
   py_send("*store_nodes "+setname + " all_selected")
   py_send("*set_select_distance 0.001") 

def find_node_set_id(name):
#   get set id number in mentat database
  n = py_get_int("nsets()")
  for i in range(1,n+1):
    id = py_get_int("set_id(%d)" % i)
    sname = py_get_string("set_name(%d)" %id)
    if( sname == name):
          return id 
  return -1
  
def get_node_id(name,number) :
# get node number with set name
       id=find_node_set_id(name)
       if  id >= 0 :
          node_no=py_get_int("nset_entries(%d)" %id)
          node_id=py_get_int("set_entry(%d,"%id+str(number)+")" )
          print "set name :",name,number,"-th node id ===",node_id
          return node_id
       else :
           print " Node set ", name ," is not defined"

def find_center(set_name) :
     node_id=get_node_id(set_name,1)
     x1=py_get_float("node_x(%d)" %node_id) 
     y1=py_get_float("node_y(%d)" %node_id) 
     z1=py_get_float("node_z(%d)" %node_id) 
#     print "first node coordinate ",x1,y1,z1
     node_id=get_node_id(set_name,2)
     x2=py_get_float("node_x(%d)" %node_id) 
     y2=py_get_float("node_y(%d)" %node_id) 
     z2=py_get_float("node_z(%d)" %node_id) 
#     print "second node coordinate ",x2,y2,z2
     xc,yc,zc=(x1+x2)/2,(y1+y2)/2,(z1+z2)/2
     print "center node coordinate ",xc,yc,zc     
     return xc,yc,zc

   

def read_path_sets() :
   tsset=["ATS1","ATS2","ATS3","ATS4","ATS5"]
   teset=["ATE1","ATE2","ATE3","ATE4","ATE5"]
   lsset=["ALS1","ALS2","ALS3","ALS4","ALS5"]
   leset=["ALE1","ALE2","ALE3","ALE4","ALE5"]
   hset=["HFL","HFC","HFR","HCL","HCC","HCR","HRL","HRC","HRR"]
   trans_no=len(tsset)
   longi_no=len(lsset)
   history_no=len(hset)
   for i in range(0,trans_no) :
     xc,yc,zc=find_center(tsset[i])
     save_set(tsset[i],xc,yc,zc)
     xc,yc,zc=find_center(teset[i])
     save_set(teset[i],xc,yc,zc)
   for i in range(0,longi_no) :
     xc,yc,zc=find_center(lsset[i])
     save_set(lsset[i],xc,yc,zc)
     xc,yc,zc=find_center(leset[i])
     save_set(leset[i],xc,yc,zc)
   for i in range(0,history_no) :
     xc,yc,zc=find_center(hset[i])
     save_set(hset[i],xc,yc,zc)

def main():
#   fname=py_get_string("model_name()")
   py_send("*set_undo off")
   read_path_sets()
   py_send("*set_undo on")   

     
if __name__ == '__main__':
    main()
