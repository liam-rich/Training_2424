from abc import ABC, abstractmethod

class Employee(ABC):

    # class-level counter (shared across all employees)
    _id_counter = 1000

    def __init__(self, name, salary):
        self.name = name #public
        self._salary = salary # protected (convention)
        
        #auto-generate unique ID
        Employee._id_counter +=1
        self.__employee_id = Employee._id_counter # private

    def get_employee_id(self):
        return self.__employee_id
    
    @abstractmethod
    def calculate_pay(self):
        pass

    def display_info(self):
        print(
            f"Name: {self.name}, "
            f"Salary: {self._salary}, "
            f"ID: {self.__employee_id}"
        )


class SalariedEmployee(Employee):

    def calculate_pay(self):
        return self._salary

    

emp = SalariedEmployee("Abdul","65000")

print(emp.name)
print(emp._salary) #Python doesn't enforce protected, but not to developers, don't touch this outside the class
#unless you have a good reason

#print(emp.__employee_id) #does not work and private is enforced
print(emp.get_employee_id())

print(emp._Employee__employee_id) #name mangling: python rewrites __employee_id as _Employee__employee_id behind the scenes
#in Python the purpose of private access modifier is not security, but it will prevent accidental access
#or accidental overriding in subclasses

emp.display_info()



