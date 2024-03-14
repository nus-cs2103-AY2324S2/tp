[![CI Status](https://github.com/se-edu/addressbook-level3/workflows/Java%20CI/badge.svg)](https://github.com/AY2324S2-CS2103T-T09-2/tp/actions)

[![codecov](https://codecov.io/gh/AY2324S2-CS2103T-T09-2/tp/graph/badge.svg?token=BMICX593IT)](https://codecov.io/gh/AY2324S2-CS2103T-T09-2/tp)

![Ui](docs/images/Ui.png)

# Introduction
Welcome to BookKeeper, your solution for efficient customer management for **small business owners in the floral
industry**. <br>

This application is designed to streamline customer lists and enhance productivity for individuals operating
small businesses with limited resources and manpower. With BookKeeper, you can easily manage customer information, track
orders, and personalize your customer interactions, all through a user-friendly command-line interface.

For the detailed documentation of this project, see the [BookKeeper](https://ay2324s2-cs2103t-t09-2.github.io/tp/).

# Acknowledgement

The project simulates an ongoing software project for a desktop application (called _AddressBook_) used for managing
  contact details.
* It is written in OOP fashion. 
* It provides a reasonably well-written code base bigger (around 6 KLoC)
* It comes with a reasonable level of user and developer documentation.

This project is based on the AddressBook Level-3 project and is a part of the [se-education.org](https://se-education.org/addressbook-level3) initiative.

# Features

## Customer Feature

### 1. Add Customer
Adds a customer to the customer's storage.

Example: `add <customer name>`

Expected output:
```
Successfully added <customer name> to your list!
```

### 2. Delete Customer
Delete selected customer from the customer's storage.

Example: `delete <customer name>`

Expected output:
```
Successfully removed <customer name> from the list
```

### 3. Edit Customer
Edit selected customer information by adding more information such as Age, Phone Number, Past purchased record, 
Birthday, Address.

Example: `edit <customer name>`

Expected output:
```
Successfully edit <customer name> information
```

### 4. VIP Customer
Indicating that this specific customer is important.

Example: `vip <customer name>`

Expected output:
```
Successfully set <customer name> as VIP
```

## Order Feature

### 1. Add Order
Adds an order into the order's storage, together with the customer name.

Example: `add <customer name, order, due date>`

Expected output:
```
An order has been added for <customer name>
```

### 2. Delete Order
Delete order of the specific customer.

Example: `delete <customer name, order>`

Expected output:
```
<customer name> order of <(order item)> has been cancelled
```

### 3. Edit Order
Edit the customer's order.

Example: `edit <customer name, old order, new order>`

Expected output:
```
Successfully edit <customer name> order
```

### 4. Prioritize Order
Prioritize the customer's order.

Example: `prioritize <customer name, order>`

Expected output:
```
Successfully prioritized <customer name, order>
```

## Viewing Feature

### 1. View Customer Detail
View customer information, such as Name, Age, Phone Number, Past purchase record, Birthday, Address.

Example: `view <customer name>`

Expected output:
```
Jasper Tan info: [...]
Tan Qing Yong info : [...]
```

### 2. View Order Detail
View Order information, such as order by when, who order, quantity.

Example: `view <order>`

Expected output:
```
Petite Bouquet: 
1. total of <quantity> order by <customer name> on <date> 
2. total of <quantity> order by <customer name> on <date> 
[...]
```

### 3. View Statistics
Show how many orders has been completed.

Example: `stat`

Expected output:
```
<a list of stat>
```

### 4. View Ranking
View which florist sell the best and total sell quantity.

Example: `view ranking`

Expected output:
```
Top 1: Preserved Hydrangea Bouquet, total sold of <quantity> 
[...]
```

## Sorting Feature

### 1. Sort Order by Date
Sort when the order is due based on the date (excluding prioritize order).

Example: `sort orderdate`

Expected output:
```
1. Jasper Tan, Eternal Love Preserved Red Rose Flower Bouquet, 24 Feb 2024 
2. Tan Qin Yong, Mocha Romance Bouquet, 1 Apr 2024 
[...]
```

### 2. Sort Order by order type
Sort depending on what kind of order.

Example: `sort ordertype`

Expected output:
```
Mocha Romance Bouquet:
1. Jasper Tan, 24 Feb 2024 
2. QinYong, 1 Apr 2024 
[...]
```

### 3. Sort Customer name by alphabetical order
sort the customer list.

Example: `sort customer`

Expected output:
```
1. Jasper Tan
2. Tan Qing Yong 
[...]
```

## Notification/ Reminder/ Deadline/ Progress Tracker

### 1. Deadline approaching soon for an order (within a week)
Send notification when order is due within a week.

### 2. Progress Bar
Show how many order left to be done.

Example: `progress`

Expected output:
```
Left <quantity> 
Done <quantity> 
Pending <Quantity>"
```

## Usage Help

### 1. Autocomplete
Show all possible next commands based on currently keyed in values.

Expected output:
```
/modi(fy)
```

### 2. User manual
Shows a list of commands that can be used.

Example: `help`

Expected output:
```
Commands list: 
1. add. Adds a new customer to the list. Example: "add..." 
[...]
```

