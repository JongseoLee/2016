from py_mentat import *
# from py_post import *


def save_set(setname,x,y,z) : 
   py_send("*select_clear")
   py_send("*select_method_point_dist")
   tol=py_get_float("T")/2
   print "Tolerance of finding nearest point ", tol
   py_send("*set_select_distance "+str(tol)) 
   biased_x=tol/15 # it is for avoiding no search when half location
   py_send("*select_nodes " + str(x+biased_x)+","+str(y)+","+str(z))
   py_send("*store_nodes "+setname + " all_selected")
   py_send("*set_select_distance 0.001") 

def find_index(set,point_number) : 
   index = 0
   for k in range(0,len(set)) :
      if point_number == set[k] :
        index= k                
   print point_number, "is in ",set, " index is ", index
   return index


def create_path_sets(fname) :
   tsset=["ATS1","ATS2","ATS3","ATS4","ATS5"]
   teset=["ATE1","ATE2","ATE3","ATE4","ATE5"]
   lsset=["ALS1","ALS2","ALS3","ALS4","ALS5"]
   leset=["ALE1","ALE2","ALE3","ALE4","ALE5"]
   hset=["HFL","HFC","HFR","HCL","HCC","HCR","HRL","HRC","HRR"]
   ts_pt=[25,24,23,22,21]  
   te_pt=[5,4,3,2,1]
   ls_pt=[25,20,15,10,5]
   le_pt=[21,16,11,6,1]
   h_pt=[25,15,5,23,13,3,21,11,1]
   trans_no=len(tsset)
   longi_no=len(lsset)
   py_send("*define trans_path_no "+str(trans_no))
   py_send("*define longi_path_no "+str(longi_no))

   ts_flag=0
   te_flag=0
   ls_flag=0
   le_flag=0
   h_flag=0
   for i in range(1,trans_no*longi_no+1) :
      if i in ts_pt :
        x=py_get_float("point_x(%d)" %i) 
        y=py_get_float("point_y(%d)" %i) 
        z=py_get_float("point_z(%d)" %i) 
        ts_index=find_index(ts_pt,i) 
        save_set(tsset[ts_index],x,y,z)
        print "Path Point Coordinate ",i,tsset[ts_index], x,y,z                                         
        ts_flag=ts_flag+1
      if i in te_pt :
        x=py_get_float("point_x(%d)" %i) 
        y=py_get_float("point_y(%d)" %i) 
        z=py_get_float("point_z(%d)" %i) 
        te_index=find_index(te_pt,i) 
        save_set(teset[te_index],x,y,z)
        print "Path Point Coordinate ",i,teset[te_index], x,y,z                                
        te_flag=te_flag+1
      if i in ls_pt :
        x=py_get_float("point_x(%d)" %i) 
        y=py_get_float("point_y(%d)" %i) 
        z=py_get_float("point_z(%d)" %i) 
        ls_index=find_index(ls_pt,i)         
        save_set(lsset[ls_index],x,y,z)
        print "Path Point Coordinate ",i,lsset[ls_index], x,y,z                        
        ls_flag=ls_flag+1
      if i in le_pt :
        x=py_get_float("point_x(%d)" %i) 
        y=py_get_float("point_y(%d)" %i) 
        z=py_get_float("point_z(%d)" %i) 
        le_index=find_index(le_pt,i) 
        save_set(leset[le_index],x,y,z)
        print "Path Point Coordinate ",i,leset[le_index], x,y,z                
        le_flag=le_flag+1
      if i in h_pt :
        x=py_get_float("point_x(%d)" %i) 
        y=py_get_float("point_y(%d)" %i) 
        z=py_get_float("point_z(%d)" %i) 
        h_index=find_index(h_pt,i) 
        save_set(hset[h_index],x,y,z)
        print "History Point Coordinate ", i,hset[h_index], x,y,z        
        h_flag=h_flag+1

def main():
   fname=py_get_string("model_name()")
   py_send("*set_undo off")  
   create_path_sets(fname)
   py_send("*set_undo on")   

     
if __name__ == '__main__':
    main()
