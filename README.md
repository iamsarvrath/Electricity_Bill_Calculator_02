# Electricity Bill Calculator

A simple responsive electricity bill calculator built with Java Servlet and JSP.

## Tariff

- First 50 units: Rs. 3.50 per unit
- Next 100 units: Rs. 4.00 per unit
- Next 100 units: Rs. 5.20 per unit
- Above 250 units: Rs. 6.50 per unit

## Run

Build the WAR with:

```bash
mvn clean package
```

Deploy `target/electricity-bill-calculator.war` to a Jakarta Servlet 6 compatible server such as Tomcat 10.1, then open `/electricity-bill-calculator/`.
