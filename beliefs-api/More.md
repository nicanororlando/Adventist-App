# Para cerrar actividades en el puerto que estamos usando:

netstat -ano | findstr LISTENING | findstr 8080
taskKill -PID 4184 -F
