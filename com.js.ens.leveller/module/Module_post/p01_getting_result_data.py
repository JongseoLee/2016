from py_mentat import *
from py_post import *


def find_node_set_id(name):
#   get set id number in mentat database
  n = py_get_int("nsets()")
  for i in range(1,n+1):
    id = py_get_int("set_id(%d)" % i)
    sname = py_get_string("set_name(%d)" %id)
    if( sname == name):
          return id 
  return -1
  
def get_node_id(name) :
# get node number with name
#    for n in range(0,len(name)) :
       id=find_node_set_id(name)
       if  id >= 0 :
#         print name[n],id
          node_no=py_get_int("nset_entries(%d)" %id)
          node_id=py_get_int("set_entry(%d,1)" %id)
          return node_id
#          print "set no",id,node_no,node_id
#          py_send("*define "+ name[n] + " " +str(node_id))

       else :
#          py_send("*define "+ name[n] + " is not defined")
           print " Node set ", name ," is not defined"
            
def create_pathplot(start_node,end_node,txt_filename):
    py_send("*pathplot_clear")
    py_send("*set_pathplot_path")
    ns=get_node_id(start_node)
    ne=get_node_id(end_node)
    print "ns and ne",ns,ne
    py_send(str(ns)+" "+str(ne)+"  #")    
    py_send("*pathplot_add\n"+"Arc Length\n"+"Deformed Coordinate Y"  )
    py_send("*pathplot_fit \n")
    py_send("*pathplot_write "+str(txt_filename)+" yes")

def create_histplot(node,txt_filename):
    py_send("*history_clear")
    py_send("*set_history_locations")
    node_no=get_node_id(node)
    py_send(str(node_no)+"  #")    
    py_send("*history_collect 0 9999999999 1 ")
    py_send("*history_add_var Time Deformed Coordinate Y \n"  )
    py_send("*history_fit \n")
    py_send("*history_write "+str(txt_filename)+" yes")


def create_path_plot_file(fname) :
#   print "start path plot"
   tsset=["ATS1","ATS2","ATS3","ATS4","ATS5"]
   teset=["ATE1","ATE2","ATE3","ATE4","ATE5"]
   lsset=["ALS1","ALS2","ALS3","ALS4","ALS5"]
   leset=["ALE1","ALE2","ALE3","ALE4","ALE5"]
   hset=["HFL","HFC","HFR","HCL","HCC","HCR","HRL","HRC","HRR"]
   pname_label=["Trans","Longi"]
   plable=["_1","_2","_3","4","5"]
   trans_no=len(tsset)
   longi_no=len(lsset)
   history_no=len(hset)
   j=0
   for n in range (0,trans_no) :
        cur_inc=py_get_int("post_data(increment)")
        pathfile=fname+"_"+pname_label[j]+plable[n]+"_inc_"+str(cur_inc)+".txt"
        print " result file infor. ==",cur_inc, pathfile
        print tsset[n],teset[n]
        create_pathplot(tsset[n],teset[n],pathfile)
   j=1        
   for n in range (0,longi_no) :
        pathfile=fname+"_"+pname_label[j]+plable[n]+"_inc_"+str(cur_inc)+".txt"
        print pathfile
        print lsset[n],leset[n]
        create_pathplot(lsset[n],leset[n],pathfile)


def create_histroy_plot_file(fname) :
   hset=["HFL","HFC","HFR","HCL","HCC","HCR","HRL","HRC","HRR"]
   history_no=len(hset)
   for n in range (0,history_no) :
        hfile=fname+"_history_"+str(hset[n])+".txt"
        print hfile
        create_histplot(hset[n],hfile)

def main():
   fname=py_get_string("model_name()")
   py_send("*define model "+fname)
   print fname
   pname = fname+"_"+py_get_string("job_name()")+".t16"
   py_send("*post_open "+pname)
   create_path_plot_file(fname)   
   py_send("*post_skip_to_last")
   create_path_plot_file(fname)
   py_send("*post_rewind")
#   create_histroy_plot_file(fname)    
#   py_send("*post_close ")  

     
if __name__ == '__main__':
    py_connect("",40007)
    main()
    py_disconnect()

