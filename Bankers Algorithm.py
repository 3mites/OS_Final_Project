Amount_Process = int(input("How many Process: "))
Amount_Resources = int(input("How many Resources: "))

Amount_Process_Iterator=1
Allocation_Amount=1
Allocation_iterator=1
Processes=[]
Max_Need=[]
Available_Work=[[]]
Need=[]
Order=[]

for x in range(0,Amount_Process):
    Need.append([])
    Processes.append([])
    Max_Need.append([])

for x in range(0,Amount_Resources):
    Amount_AvailWork = int(input("Available work for Resource {}: ".format(x+1)))
    Available_Work[0].append(Amount_AvailWork)

for x in range(0,Amount_Process):
    print("Allocation for P{}".format(Amount_Process_Iterator))
    for y in range(0,Amount_Resources):
        Allocate=int(input("Enter Amount of element {} Allocated: ".format(Allocation_iterator)))
        Processes[Amount_Process_Iterator-1].append(Allocate) #p1,p2,p3
        Allocation_iterator+=1 #Resource 1,2,3
    Allocation_iterator = 1
    Amount_Process_Iterator+=1
Amount_Process_Iterator=1

for x in range(0,Amount_Process):
    print("Max Needed for P{}".format(Amount_Process_Iterator))
    for y in range(0,Amount_Resources):
        Allocate=int(input("Enter Amount of Max Needed for element {} : ".format(Allocation_iterator)))
        Max_Need[Amount_Process_Iterator-1].append(Allocate) #p1,p2,p3
        Allocation_iterator+=1 #Resource 1,2,3
    Allocation_iterator = 1
    Amount_Process_Iterator+=1

def Bankers_Algorithm(Processes,Max_Need,Available_Work):
    #generate need
    Need_Iterator = 0
    Element_Iterator = 0
    Resultant_Available_Work=[]
    Processes_Copy=Processes
    Success_Count = 1
    Tuple_Need = tuple(Need)

    Available_Work_Iterator=0
    for x in range(0,5): #X element of Y list
        for y in range(0,3): #depending on how many resources
            Max_Need_Value=Max_Need[Need_Iterator][Element_Iterator] #Max_Need[0][0]
            Process_Value=Processes[Need_Iterator][Element_Iterator] #Process_Value[0][0]
            Result=Max_Need_Value-Process_Value
            Need[Need_Iterator].append(Result)
            Element_Iterator += 1
        Element_Iterator = 0
        Need_Iterator += 1
    Need_Iterator=0
    while len(Need) != 0:
        Need_Value=Need[Need_Iterator][Element_Iterator]
        Available_Work_Value=Available_Work[Available_Work_Iterator][Element_Iterator]
        if Available_Work_Value >= Need_Value:
            Element_Iterator += 1     #Check Next Need Value and compare to Next Avialable Work Value
            Success_Count += 1       #if success count =3 then append meaning can allocate
            if Success_Count == 3:
                for y in range(0,3):
                    Add = Available_Work[-1][y] + Processes_Copy[Need_Iterator][y]
                    Resultant_Available_Work.append(Add)
                Available_Work.append(Resultant_Available_Work)
                Index=Tuple_Need.index(Need[Need_Iterator])
                Order.append(Index)
                Processes_Copy.pop(Need_Iterator)
                Need.pop(Need_Iterator)
                Resultant_Available_Work=[]
                Success_Count=1
                Element_Iterator=0
                Available_Work_Iterator += 1
                if Need_Iterator >= len(Need):
                    Need_Iterator = 0  # go back to the start
        else:
            Need_Iterator+=1 #move on to next process

    print("Available Work")
    for x in range(0, len(Available_Work)):
        print(Available_Work[x])

    print("Need")
    for x in range(0, len(Tuple_Need)):
        print("P{}:{} ".format(x+1,Tuple_Need[x]))

    print("Order")
    for x in range(0,len(Order)):
        print("P{}".format(Order[x]+1))

print("Allocation")
for x in range(0,len(Processes)):
    print("P{}:{} ".format(x+1,Processes[x]))

print("Max_Need")
for x in range(0,len(Max_Need)):
    print("P{}:{} ".format(x+1,Max_Need[x]))



Bankers_Algorithm(Processes,Max_Need,Available_Work)