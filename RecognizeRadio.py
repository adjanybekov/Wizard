import urllib2
from datetime import datetime
from datetime import timedelta
import time

#The classese of lookub
import sys
import os
import subprocess

# Name of the file stream and how long to record a sample
URL = "http://77.235.11.2:8000/"
RECORD_SECONDS = 30
PERIOD = 7200
#a = urllib2.urlopen("http://tumar.fm:8000/online")


try:
   urllib2.urlopen("http://tumar.fm:8000/online")
   
except urllib2.HTTPError, err:
   if err.code == 404:
      urllib2.urlopen(URL) 
      print "Recording from Hit Fm"
   else:
       raise


# Only run if the user types in "start"


# Open the file stream and write file


for i in range(380):
   CurrentTime = "time = ", datetime.now()
   filename = 'python.mp3'
   f=file(filename, 'wb')
   url=urllib2.urlopen(URL)

   # Basically a timer
   t_start = datetime.now()
   t_end = datetime.now()
   t_end_old = t_end

   # Record in chunks until

   print "Recording Radio!"
   while t_end-t_start < timedelta(seconds=RECORD_SECONDS):
       f.write(url.read(1024))
       t_end = datetime.now()
   # Or one big read?
   #f.write(url.read(1024*517))
   f.close()



#*******************************************************************************************************************
  #This code starts recorded Music 
   #import subprocess
   #FILEPATH = "/home/e211104/Desktop/bython/python.mp3"
   #PLAYERPATH = "/usr/bin/vlc"

   #subprocess.call([PLAYERPATH, FILEPATH])






   try:
       import json
   except ImportError:
       import simplejson as json

   sys.path.insert(0, "../../echo/echoprint-server/API")
   import fp
   #Write the ID name in a txt document


   codegen_path = os.path.abspath("../../echo/echoprint-codegen/echoprint-codegen")

   def codegen(FILE, start=0, duration=50):
       proclist = [codegen_path, os.path.abspath(FILE), "%d" % start, "%d" % duration]
       p = subprocess.Popen(proclist, stdout=subprocess.PIPE)                      
       code = p.communicate()[0]                                                   
       return json.loads(code)
    
   def lookup(FILE):
       codes = codegen(FILE)
        
       if len(codes) and "code" in codes[0]:
           decoded = fp.decode_code_string(codes[0]["code"])
           result = fp.best_match_for_query(decoded)
           print "Got result:", result
           if result.TRID:
                 
               #I want to print thr current time and the name of music together 
               
               ID = "ID: %s" % (result.TRID)
               myFile.write(ID+str(CurrentTime))#+"\n"+"Artist: %s" % (result.metadata.get("artist"))+"\n"+"Song: %s" % (result.metadata.get("track"+"\n"))+"\n")
               myFile.write("Artist: %s" % (result.metadata.get("artist")))
               myFile.write("Song: %s" % (result.metadata.get("track"))+"\n")
               #print  ID+str(CurrentTime)
               #print "Artist: %s" % (result.metadata.get("artist"))
               #print "Song: %s" % (result.metadata.get("track"+"\n"))
           else:
               #print "No match. This track may not be in the database yet."
               Unmatch = "No match. This track may not be in the database yet."
               myFile.write(Unmatch+str(CurrentTime)+"\n")
               
       else:
           Undecode = "Couldn't decode" + str(FILE)
           myFile.write(Undecode)
           
           #print "Couldn't decode", FILE
               
   myFile = open('/home/e211104/Desktop/LookUpresults.txt','a+')
   
   
   #sys.stdout = myFile
   #if __name__ == "__main__":
       #if len(sys.argv) < 2: 
           #print >>sys.stderr, "Usage: %s <audio file>" % sys.argv[0]
           #sys.exit(1)
   #f.close()
   lookup('/home/e211104/Desktop/bython/python.mp3')
   myFile.close()


