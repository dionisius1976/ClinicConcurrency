# ClinicConcurrency
Concurrency 

Программа запускае поток Admin, который заполяет списокк клиники 
4-мя клиентами с домашними питомцами, а затем с задержкой
проверяет правильность имён питомцев, если они были изменены, то
админ присваивает им изначальные.
Параллельно запускается 4 потока User, которые в цикле меняют 
правльные имена питомцев на их зеркальное отображение.
