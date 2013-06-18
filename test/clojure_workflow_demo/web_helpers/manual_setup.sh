;;;; Some manual commands, to make sure things work from the outside.
;;;; Use the stuff here and a browser.

;;;; ___________________________________________________________________________
;;;; ---- Curl commands to send to a shell ----

;;;; Dev port

curl -X PUT 'http://localhost:7601/inc-value-in-domain-model'


;;;; The usual `lein ring` port

curl -X PUT 'http://localhost:3000/inc-value-in-domain-model'
