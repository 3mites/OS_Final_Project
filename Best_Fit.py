Dimension = int(input("How many Partitions: "))
Amount_Process = int(input("How many Process: "))

Partition_Count = 0
Process_Count = 0

Memory_Partitions = []
Processes = []
Order = []
Can_Fit = []
Sorted_Can_Fit = []

for x in range(0, Dimension):
    Partitions_Prompt = int(input("Enter Partition {}: ".format(Partition_Count + 1)))
    Memory_Partitions.append(Partitions_Prompt)
    Partition_Count += 1
print("\n")

for x in range(0, Amount_Process):
    Processes_Prompt = int(input("Enter Process {}: ".format(Process_Count + 1)))
    Processes.append(Processes_Prompt)
    Process_Count += 1
print("\n")

counter = 0
checker = 0
Memory_Partitions_Copy=tuple(Memory_Partitions)


while len(Processes) != 0:
    Len_Memory_Partitions_Holder = len(Memory_Partitions)

    for x in range(0, Len_Memory_Partitions_Holder):
        """
        In this for loop determining of best fit is done by subtracting each index of memory partitions to
        the current process, if the result is negative(<0) then the process can not fit to
        that allocation, only the positive(<=0) are collected and used as evaluation to determine
        which partition the process is best fit to.
        """
        Difference = int(Memory_Partitions[counter]) - int(Processes[0])
        if Memory_Partitions[counter] - int(Processes[0]) >= 0:
            Can_Fit.append(Memory_Partitions[counter])
        counter += 1

    #Current processs cannot fit into any memory block since the for loop above
    #determined that there are no suiting allocation for the current
    #process, therefore no allocation
    if len(Can_Fit) == 0:
        Order.append(str(Processes[0]) + " No Allocation")
        Processes.pop(0)

    #Process can fit to x amount of memory block, can fit
    #Line 58: Sorting the difference list, first index would be the best fit for the process
    #Line 59: Get the index/Partition Number of the best fit memory block
    #Line 61: Process succesfully allocated, move on to next process
    #Line 62: Block has been occupied, can no longer be used by next process
    elif len(Can_Fit) >= 1:
        Sorted_Can_Fit = sorted(Can_Fit)
        best_fit_index = Memory_Partitions_Copy.index(Sorted_Can_Fit[0])
        Order.append(str(Processes[0]) + ": Partition " + str(best_fit_index+1))
        Processes.pop(0)
        Memory_Partitions.pop(0)


    #reinitializing evaluation
    counter = 0
    Can_Fit = []
    Sorted_Can_Fit = []
    checker += 1

for x in range(0, len(Order)):
    print(Order[x])
