#
#
from py_mentat import *
from py_post import *


def get_fname():  # get file name
   fname = py_get_string("model_name()")
   iname = fname+"_"+py_get_string("job_name()")+".dat"
   #   print "File names ==",fname,iname
   return fname,iname

def check_block(s,b_flag) :
# Check Block      
     if s[0:10] == "connectivi" :
         print "Block Connectivity "
         b_flag = 1
     if s[0:10] == "coordinate" :
         print "Block Coordinates"          
         b_flag = 2
     if s[0:10] == "isotropic " :
         print "Block Isotropic"          
         b_flag = 3      
     if s[0:10] == "ELEM SECTI" :
         print "Block Element Section"          
         b_flag = 4      
     if s[0:10] == "NODE SECTI" :
         print "Block Node Section"          
         b_flag = 5               
     if s[0:10] == "SURFACE SE" :
         print "Block Surface Section"          
         b_flag = 6   
     if s[0:10] == "END SECTIO" :

         print "End of model Section"          
         b_flag = 999                                    
#  Check Block         
     return b_flag 

def change_format(sr) :
 # print sr
  ss=sr[15:]
  if "+" in ss :
    i=ss.index("+")+15
 #   print "Plus",sr[15:],i    
    sr1=sr[:i]+"e"+sr[i:]
 #   print sr1
  if "-" in ss :
    i=ss.index("-")+15
 #   print "Minus",sr[15:],i
    sr1=sr[:i]+"e"+sr[i:]
 #   print sr1      
  return sr1 

  
def store_coordinate(s1,k0,rf,wf) :
  node_no,cx,cy,cz=[],[],[],[]
  wf.writelines(s1)
  s1=rf.readline()
  k0=k0+1
  wf.writelines(s1)
  var=s1.split()
  var[0],var[1]=int(var[0]),int(var[1])
  print " variables in node section",var
  print "current line no ",k0 
  if var[1] == 0 :
    print "remeshed data "
    kc=0
    ss="  "
    while ss != "isotropic " :
      s1=rf.readline() 
      ss=s1[0:10]
      kc=kc+1
    print "node numner is ",kc-1
    var[1]=kc-1 
    rf.seek(0) 
    for l in range(1,k0) :
      s1=rf.readline()
    for i in range(1,var[1]+1) :
      s1=rf.readline()
      k0=k0+1 
      print "Readed Nodal Coordinate ",s1
      ss=s1.split()
      node_no.append(int(ss[0]))
      cx.append(float(ss[1]))
      cy.append(float(ss[2]))
      cz.append(float(ss[3]))
      wf.writelines(s1)
  else :
    for i in range(1,var[1]+1) :
      coord=[]   
      s1=rf.readline()
      k0=k0+1 
#      print k0,"Readed Nodal Coordinate ",s1
      sr=s1[0:10]
      coord.append(sr)
      sr=s1[10:30]
      sr1=change_format(sr)
      coord.append(sr1)
      sr=s1[30:50]
      sr1=change_format(sr)
      coord.append(sr1)
      sr=s1[50:70]
      sr1=change_format(sr)
      coord.append(sr1)
      coord[0],coord[1],coord[2],coord[3]=int(coord[0]),float(coord[1]),float(coord[2]),float(coord[3])
#      print "Stored Nodal Coordinate  ",coord
      node_no.append(coord[0])
      cx.append(coord[1])
      cy.append(coord[2])
      cz.append(coord[3])
      wf.writelines(s1)
  k1=k0  
  return k1,node_no,cx,cy,cz

def change_node_sect(s1,k0,rf,wf) :
  wf.writelines(s1)
  s1=rf.readline()
  k0=k0+1
  wf.writelines(s1)
  var=s1.split()
  var[0],var[1]=int(var[0]),int(var[1])
  print " No of  Node",var[0], " No. of variables ", var[1]
  for i in range(1,var[1]+1) :  
    print " start nodal variable change ", i
    s1=rf.readline()
    k0=k0+1 
    wf.writelines(s1)
    #print " node section",s1
    nvar=s1[20:25]
    ndof=int(s1[0:10])
    nnode=int(s1[10:20])
    tdof=ndof*nnode
    sub=(tdof) % 8
    print "ndof,nnode,tdof,sub",ndof,nnode,tdof,sub
    if sub == 0 : 
      nlines=int(tdof/8)+1
    else   :
      nlines=int(tdof/8)+2
    nodex=[]
    print "nodal variables ",nvar, nlines 
    nvars=nvar.strip()
    print "nvar ====",nvar.strip(),"||"
    if nvars=="dsxts" or nvars=="dsxs" :  # or nvars=="dynvs"  or nvars=="dynas" --> edit vel.
     print "   variables is changed :  ",nvars
     for j in range(1,nlines) :
      ncol=8
      s1=rf.readline()
      k0=k0+1 
      l=s1.split()
      if j == nlines-1 :
         print "last line ",l
         ncol=sub
      s2 =""
      for j1 in range(0,ncol) :
        dof = ((j-1)*8+j1+1)% ndof
   #     print ((j-1)*8+j1+1), dof
   #     print l
        if dof == 1 :
           l[j1] = 0.0  #  reset displacements
           if l[j1] >= 0 :
             l[j1]=(' %19.13e'%l[j1])
           else :
             l[j1]=(' %19.12e'%l[j1])             
        else :
           l[j1] = s1[(j1)*20:(j1+1)*20]
        s2=s2+l[j1]
      s2=s2+"\n"
      wf.writelines(s2)
    else :
      print "   variables is not changed :  ",nvars
      for j in range(1,nlines) :
        s1=rf.readline()
        k0=k0+1 
        wf.writelines(s1)
  s1=rf.readline()
  print "end of node section ",s1
  if s1[0:10] == "SURFACE SE" :  
    rf.seek(0) 
    for l in range(1,k0) :
      s1=rf.readline()
  else :
    k0=k0+1 
    wf.writelines(s1)    
  k1=k0
  return k1

def change_surf_sect(s1,k0,rf,wf,node_no,cx,cy,cz) :
#  This function is change surface section as original shape.
  wf.writelines(s1)
  for i in range (0,5) :
    s1=rf.readline()
    k0=k0+1
    wf.writelines(s1)
  var=int(s1)
  print "no of nodes in surface section ",var
  # Coordinate Block
  for i in range(0,var) :
    s1=rf.readline()
    k0=k0+1
    varc=s1.split()
#    print varc
    cordx=('%20e'%cx[i])
    cordy=('%20e'%cy[i])
    cordz=('%20e'%cz[i])    
#    cordy=('%20e'%float(varc[2]))
#    cordz=('%20e'%float(varc[3]))    
    s2=s1[0:10]+cordx+cordy+cordz+"\n"
    wf.writelines(s2)
    
  s1=rf.readline()
  k0=k0+1
  wf.writelines(s1)    
  var=int(s1)
  print "ELEMENT NO ",var
  # Connectivity Block
  for i in range (0,var) :
    s1=rf.readline()
    k0=k0+1
    wf.writelines(s1)
  s1=rf.readline()
  k0=k0+1
  wf.writelines(s1)
  k1=k0
  return k1
  
def change_file(iname,oname) :
   rf=open(iname,"r")
   wf=open(oname,"w")
   s = rf.read()
   no_lines=s.count('\n')
   print (" Number of Lines ",no_lines)
   rf.seek(0) # move to first line
   k=1  # line number
   b_flag = 0 # block flag
   c_flag = 0 # Coordinate edit flag
   n_flag = 0 # node section edit flag
   s_flag = 0 # Surface Section edit flag
   s1=rf.readline()
   k=k+1
   wf.writelines(s1)
   while k<= no_lines : 
     s1=rf.readline()
     k=k+1
     b_flag=check_block(s1,b_flag)
 #    print "Line No",k,"Block No ",b_flag, s1[0:12]
     if b_flag == 0 : # Header
       wf.writelines(s1)      
     if b_flag == 1 : # Connectivity 
       wf.writelines(s1)
     if b_flag == 2 and c_flag == 0 : # Coordinate
       kr,node_no,cx,cy,cz=store_coordinate(s1,k,rf,wf)
       k=kr
       c_flag=1    
     if b_flag == 3 : # Isotropic
       wf.writelines(s1)         
     if b_flag == 4 : # Element Section
       wf.writelines(s1) 
     if b_flag == 5 and n_flag == 0 : # Node Section
       kr=change_node_sect(s1,k,rf,wf)
       n_flag=1 
       k=kr
     if b_flag == 6 and s_flag == 0: # Surface Section
       #print node_no 
       kr=change_surf_sect(s1,k,rf,wf,node_no,cx,cy,cz)
       k=kr                                     
       s_flag = 1
     else : 
#      s1 = "Skip \n"
#      wf.writelines(s1)
       continue

   rf.close() 
   wf.close() 
#   move_file="*system_command move temp.dat "+iname
#   py_send(move_file)
   return 

def main():   
   iname="plate_job1_stage01.sec"
#   oname="plate_2el_stage00.sec"
   oname="temp.sec"
   change_file(iname,oname)
   return     

   
if __name__ == '__main__':
    py_connect("",40007)
    main()
    py_disconnect()






