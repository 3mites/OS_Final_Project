Dimension=int(input("How many Partitions: "))
Amount_Process=int(input("How many Process: "))

Partition_Count=0
Process_Count=0

Memory_Partitions=[]
Processes=[]
Order=[]

for x in range(0,Dimension):
    Partitions_Prompt=int(input("Enter Partition {}: ".format(Partition_Count+1)))
    Memory_Partitions.append(Partitions_Prompt)
    Partition_Count +=1
print("\n")

for x in range(0,Amount_Process):
    Processes_Prompt=int(input("Enter Process {}: ".format(Process_Count+1)))
    Processes.append(Processes_Prompt)
    Process_Count +=1
print("\n")

def First_Fit(Memory_Partitions, Processes):
    Mem_Part_Check = 0
    partition_number = 1
    iteration = 0
    while len(Processes) != 0:
        #move on to next index of memory_partitions
        if iteration == len(Memory_Partitions):
            Order.append(str(Processes[0]) + ": No Allocation")
            Processes.pop(0)

        #check first element of process compare it to current index of partitions if fit pop element
        elif (Memory_Partitions[Mem_Part_Check] - Processes[0]) >= 0:
            Memory_Partitions[iteration] = int(Processes[0]) - int(Memory_Partitions[iteration])
            Order.append(str(Processes[0]) + ": Partition " + str(iteration + 1))
            Processes.pop(0)
            Mem_Part_Check = 0
            partition_number += 1
            iteration = 0

        # negative cant fit, move on to next index
        elif (Memory_Partitions[Mem_Part_Check] - Processes[0]) < 0:
            Mem_Part_Check += 1
            iteration += 1


First_Fit(Memory_Partitions, Processes)

for x in range(0, len(Order)):
    print(Order[x])