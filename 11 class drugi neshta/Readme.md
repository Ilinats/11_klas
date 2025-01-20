Разбира се! Ще измисля няколко задачи с по-сложен характер, които да изискват оптимизация, линейна алгебра и манипулация с масиви в NumPy. Ще добавя решенията и ще използвам само **NumPy**.

---

### Задача 1: **Оптимизация на производствена линия**

#### Описание:
Имате производствена линия, която произвежда три различни продукта. Всеки продукт изисква различно количество време за производство и различен брой ресурси (работници, машини). Вие имате ограничен брой ресурси, които трябва да разпределите между продуктите, така че да максимизирате общата печалба.

#### Вход:
- **Продукти:** "ПродуктA", "ПродуктB", "ПродуктC"
- **Изисквания за време (в часове) на продуктите:** {ПродуктA: 5 часа, ПродуктB: 8 часа, ПродуктC: 7 часа}
- **Изисквания за ресурси (брой работници, машини):** 
  - ПродуктA: [3 работници, 2 машини]
  - ПродуктB: [4 работници, 3 машини]
  - ПродуктC: [2 работници, 3 машини]
- **Достъпни ресурси:** 12 работници, 10 машини
- **Печалба от всеки продукт:** 
  - ПродуктA: 100
  - ПродуктB: 120
  - ПродуктC: 150

#### Цел:
Да разпределите ресурсите (работници и машини) между продуктите, така че да максимизирате печалбата.

---

#### Решение:

```python
import numpy as np

# Входни данни
products = ["ПродуктA", "ПродуктB", "ПродуктC"]
time = np.array([5, 8, 7])  # Изисквания за време за всеки продукт (в часове)
resources = np.array([[3, 2], [4, 3], [2, 3]])  # Изисквания за ресурси (работници, машини)
profits = np.array([100, 120, 150])  # Печалби от всеки продукт
available_resources = np.array([12, 10])  # Достъпни ресурси (работници, машини)

# Максимизация на печалбата (целта)
# Ще трябва да изберем количествата на продуктите, които ще произведем, така че да максимизираме печалбата
# Разпределяме ресурсите (работници и машини) между продуктите, като използваме ограничението за наличните ресурси

# Търсим оптимално решение, като решаваме линейна система
# Нека x1, x2, x3 са количествата на продукти A, B и C, които ще произведем.

# Ограничаващите условия (работници и машини):
# 3 * x1 + 4 * x2 + 2 * x3 <= 12 (работници)
# 2 * x1 + 3 * x2 + 3 * x3 <= 10 (машини)

# Създаваме матрицата на ограниченията
A = resources  # 2x3 матрица с изискванията за работници и машини
b = available_resources  # 1D масив с общо налични ресурси

# Създаваме целевата функция (печалба)
c = -profits  # Минимизиране на негативната печалба (защото линейното програмиране минимизира)

# Решаваме проблема с линейно програмиране, но трябва да се уверим, че x1, x2, x3 са неотрицателни
# Тук ще използваме просто решаване на линейната система и изчисляване на печалбата

# Решаваме с numpy.linalg.lstsq, за да намерим решение на линейната система
# x1, x2, x3 са количествата на продуктите, които ще произведем
x, resids, rank, s = np.linalg.lstsq(A, b, rcond=None)

# Изчисляваме печалбата
profit = np.dot(x, profits)

# Извеждаме резултата
print("Оптимално разпределение на ресурсите:")
for i, product in enumerate(products):
    print(f"{product}: {x[i]:.2f} бройки")
print(f"Обща печалба: {profit:.2f}")
```

---

### Задача 2: **Планиране на транспорт**

#### Описание:
Имате три фабрики и четири магазина. Всеки магазин има различно количество търсене, а всяка фабрика има различен капацитет на производство. Трябва да разпределите количествата между фабриките и магазините така, че да минимизирате транспортните разходи.

#### Вход:
- **Транспортни разходи (в лева):** 
  - Фабрика1 -> Магазин1: 4, Фабрика1 -> Магазин2: 5, ...
- **Търсене на магазините:** Магазин1: 30, Магазин2: 40, Магазин3: 50, Магазин4: 20
- **Производствен капацитет на фабриките:** Фабрика1: 60, Фабрика2: 50, Фабрика3: 40

#### Цел:
Минимизиране на транспортните разходи.

---

#### Решение:

```python
import numpy as np

# Входни данни
factories = ["Фабрика1", "Фабрика2", "Фабрика3"]
stores = ["Магазин1", "Магазин2", "Магазин3", "Магазин4"]

# Транспортни разходи
costs = np.array([
    [4, 5, 6, 8],  # Разходи от Фабрика1 към магазините
    [3, 7, 4, 6],  # Разходи от Фабрика2 към магазините
    [6, 4, 5, 7],  # Разходи от Фабрика3 към магазините
])

# Търсене на магазините
demand = np.array([30, 40, 50, 20])

# Производствени капацитети на фабриките
supply = np.array([60, 50, 40])

# Матрица за разпределение на стоките между фабриките и магазините
allocation = np.zeros_like(costs)

# Разпределяме стоките
remaining_supply = supply.copy()
remaining_demand = demand.copy()

for i in range(len(remaining_supply)):
    for j in range(len(remaining_demand)):
        allocated = min(remaining_supply[i], remaining_demand[j])
        allocation[i, j] = allocated
        remaining_supply[i] -= allocated
        remaining_demand[j] -= allocated

# Изчисляваме транспортните разходи
total_cost = np.sum(allocation * costs)

# Извеждаме резултатите
print("Оптимално разпределение на стоките:")
for i in range(len(factories)):
    for j in range(len(stores)):
        print(f"От {factories[i]} към {stores[j]}: {allocation[i, j]:.2f} единици")
print(f"Общи транспортни разходи: {total_cost:.2f}")
```

---

### Задача 3: **Линейна регресия с ограничение на променливите**

#### Описание:
Имате набор от данни, които описват зависимостта между две променливи. Трябва да намерите линейната регресия, но с ограничение, че наклонът на правата (коефициентът на променливата) трябва да бъде между 0 и 5.

#### Вход:
- **Данни:** Точки на данни за зависимостта между променливите.

#### Цел:
Намерете линейната регресия с ограничение на наклона на правата.

---

#### Решение:

```python
import numpy as np

# Входни данни
X = np.array([1, 2, 3, 4, 5])  # Независима променлива
Y = np.array([2, 4, 6, 8, 10])  # Зависима променлива

# Намираме наклона и пресечната точка на правата чрез обикновената най-малка квадратна регресия
# Y = aX + b, като a е наклонът, а b е пресечната точка

# Стандартна формула за линейна регресия
A = np.vstack([X, np.ones_like(X)]).T
m, c = np.linalg.lstsq(A, Y, rcond=None)[0]

# Ограничаваме наклона да бъде между 0 и 5
m = np.clip(m, 0, 5

)

# Извеждаме резултатите
print(f"Наклон (ограничен): {m:.2f}")
print(f"Пресечна точка: {c:.2f}")
```

Тези задачи включват различни подходи към линейната алгебра, оптимизация и управление на ресурси. С NumPy можем да постигнем ефективно решение с ограничен набор от библиотеки!



Разбира се, ще обясня подробно какво става в този код.

### Линейна регресия и използваната формула:

Линейната регресия се използва за намиране на най-добрата права линия, която описва зависимостта между две променливи. Общата форма на линейната функция е:

\[
Y = aX + b
\]

Където:
- \( Y \) е зависимата променлива (резултатът, който искаме да прогнозираме),
- \( X \) е независимата променлива (входната стойност, с която работим),
- \( a \) е наклонът на правата (коефициентът на \( X \)),
- \( b \) е пресечната точка с ос \( Y \) (постоянната стойност).

Целта на линейната регресия е да намери стойностите на \( a \) (наклон) и \( b \) (пресечна точка), които минимизират разликата между реалните стойности на \( Y \) и прогнозните стойности от модела.

### Стъпки в кода:

1. **Създаване на матрицата \( A \)**:
   ```python
   A = np.vstack([X, np.ones_like(X)]).T
   ```
   Тази част на кода изгражда матрица, която ще се използва за решаване на линейната регресия чрез метода на най-малките квадрати.

   - **`np.ones_like(X)`**: Тази функция създава масив със същата форма като \( X \), но със стойности 1. Това ще е колоната за пресечната точка (\( b \)) на правата.
   
   - **`np.vstack([X, np.ones_like(X)])`**: Тази функция комбинира масивите \( X \) и \( np.ones_like(X) \) по вертикала. Получаваме матрица с два реда: първият ред съдържа стойностите на \( X \), а вторият ред съдържа единици. Това създава следната матрица (ако \( X \) има 5 стойности):

     \[
     A = \begin{bmatrix}
     X_1 & X_2 & X_3 & X_4 & X_5 \\
     1 & 1 & 1 & 1 & 1
     \end{bmatrix}
     \]

   - **`.T`**: Транспонира матрицата \( A \). Това означава, че редовете стават колони и така получаваме матрица със следния вид:

     \[
     A = \begin{bmatrix}
     X_1 & 1 \\
     X_2 & 1 \\
     X_3 & 1 \\
     X_4 & 1 \\
     X_5 & 1
     \end{bmatrix}
     \]

   Тази матрица съдържа входните данни \( X \) и съответните единични стойности за пресечната точка на правата (коефициентът \( b \)).

2. **Решаване на линейната система**:
   ```python
   m, c = np.linalg.lstsq(A, Y, rcond=None)[0]
   ```
   Тук използваме метода на най-малките квадрати (least squares), който решава линейната система от уравнения.

   - **`np.linalg.lstsq(A, Y, rcond=None)`**: Тази функция решава системата \( A \cdot \begin{bmatrix} m \\ c \end{bmatrix} = Y \), където:
     - \( A \) е матрицата със стойности \( X \) и единици (т.е. \( A \cdot \begin{bmatrix} m \\ c \end{bmatrix} \) представлява правата \( Y = mX + c \)),
     - \( Y \) е векторът със зависимите променливи (цели стойности),
     - \( m \) и \( c \) са неизвестните (наклонът и пресечната точка), които искаме да намерим.

   - Функцията `np.linalg.lstsq()` връща няколко стойности, но нас ни интересуват само първите две: \( m \) и \( c \) (независимо от останалите стойности, свързани с отклонението на решението).

3. **Ограничаване на наклона \( m \)**:
   ```python
   m = np.clip(m, 0, 5)
   ```
   Функцията **`np.clip(m, 0, 5)`** ограничава стойността на наклона \( m \) да бъде между 0 и 5. Това означава, че ако изчисленият наклон е по-малък от 0, той ще бъде поставен на 0; ако е по-голям от 5, ще бъде поставен на 5.

   Например:
   - Ако \( m = -2 \), след `np.clip(m, 0, 5)` стойността ще стане 0.
   - Ако \( m = 6 \), стойността ще стане 5.
   - Ако \( m = 2 \), стойността ще остане 2.

### Резюме:

- **Матрицата \( A \)** комбинира стойностите на независимата променлива \( X \) и единиците за пресечната точка.
- **Методът `np.linalg.lstsq()`** намира наклона \( m \) и пресечната точка \( c \), които минимизират разликата между предсказаните и действителните стойности на \( Y \).
- **Функцията `np.clip(m, 0, 5)`** ограничава наклона \( m \) да бъде в интервала от 0 до 5.

Това е основната логика зад този код за линейна регресия с ограничение на наклона.

Разбира се! Ето още няколко задачи, които са подобни на тези, които решаваме с numpy. Ще бъдат по-сложни и включват различни типове оптимизация и математически проблеми.

---

### Задача 1: Оптимизация на производствени разходи
**Описание:**
Имате няколко производствени линии, които произвеждат различни продукти. Всеки продукт има различни разходи на производство и се изисква определено количество ресурси. Целта е да разпределите наличните ресурси, за да минимизирате разходите за производството на определен брой продукти.

**Вход:**
```python
production = {
    "lines": ["Line1", "Line2", "Line3"],
    "products": ["ProductA", "ProductB", "ProductC"],
    "costs": {
        "Line1": {"ProductA": 10, "ProductB": 15, "ProductC": 20},
        "Line2": {"ProductA": 12, "ProductB": 18, "ProductC": 25},
        "Line3": {"ProductA": 11, "ProductB": 16, "ProductC": 19},
    },
    "resources_needed": {
        "ProductA": 5,
        "ProductB": 3,
        "ProductC": 7,
    },
    "available_resources": 50,
}
```

**Указания:**
- Постройте матрица за разходите на различните производствени линии и продукти.
- Определете как да разпределите наличните ресурси между производствените линии, така че да минимизирате разходите.

---

### Задача 2: Векторна регресия
**Описание:**
Дадени са различни входни стойности за няколко независими променливи. Използвайте линейна регресия, за да предскажете зависимостта на зависимата променлива от входните стойности.

**Вход:**
```python
data = {
    "X1": [1, 2, 3, 4, 5],
    "X2": [2, 4, 6, 8, 10],
    "Y": [3, 6, 9, 12, 15],
}
```

**Указания:**
- Постройте матрица на входните данни (X1, X2).
- Използвайте линейна регресия, за да намерите коафициентите на модела.
- Оценете резултатите от модела.

---

### Задача 3: Линейна оптимизация с ограничения
**Описание:**
Имате два продукта, които искате да произвеждате. За всеки продукт имате различни изисквания за ресурси и различна печалба. Необходими са ви производствени капацитети и трябва да разпределите тези капацитети, така че да максимизирате печалбата.

**Вход:**
```python
optimization = {
    "products": ["ProductX", "ProductY"],
    "profits": {"ProductX": 50, "ProductY": 40},
    "resource_requirements": {"ProductX": 3, "ProductY": 2},
    "available_resources": 100,
}
```

**Указания:**
- Постройте функция, която максимизира печалбата.
- Използвайте numpy за намиране на оптимално разпределение на ресурсите между продуктите.

---

### Задача 4: Прогнозиране на стойности
**Описание:**
Дадени са исторически данни за производителност на компания, които трябва да се използват за прогнозиране на бъдещите стойности. Използвайте линейна регресия, за да предскажете производителността за следващите няколко години.

**Вход:**
```python
historical_data = {
    "years": [1, 2, 3, 4, 5],
    "performance": [100, 120, 140, 160, 180],
}
```

**Указания:**
- Постройте линейна регресия за прогнозиране на бъдещата производителност.
- Прогнозирайте стойности за следващите 3 години.

---

### Задача 5: Транспортен проблем с няколко доставчици и потребители
**Описание:**
Имате няколко доставчика и потребители, като всеки доставчик има определено количество стоки, които може да достави, а всеки потребител има нужда от определено количество стоки. Трябва да разпределите стоките, така че разходите за транспортиране да бъдат минимални.

**Вход:**
```python
transport = {
    "suppliers": ["Supplier1", "Supplier2", "Supplier3"],
    "customers": ["CustomerA", "CustomerB", "CustomerC"],
    "costs": {
        "Supplier1": {"CustomerA": 5, "CustomerB": 8, "CustomerC": 7},
        "Supplier2": {"CustomerA": 6, "CustomerB": 9, "CustomerC": 5},
        "Supplier3": {"CustomerA": 8, "CustomerB": 7, "CustomerC": 6},
    },
    "supply": {"Supplier1": 50, "Supplier2": 60, "Supplier3": 40},
    "demand": {"CustomerA": 30, "CustomerB": 50, "CustomerC": 70},
}
```

**Указания:**
- Постройте матрица на разходите за транспортиране.
- Определете как да разпределите доставките между доставчиците и потребителите, така че разходите да бъдат минимални.

---

Това са още няколко задачи, които можеш да решиш, използвайки само numpy. Ако имаш нужда от решенията или пояснения за някоя от тях, не се колебай да попиташ!

Разбира се! Ето решенията на задачите с numpy.

---

### Задача 1: Оптимизация на производствени разходи

**Решение:**
Целта е да разпределим ресурсите между производствените линии, така че да минимизираме разходите.

```python
import numpy as np

# Входни данни
production = {
    "lines": ["Line1", "Line2", "Line3"],
    "products": ["ProductA", "ProductB", "ProductC"],
    "costs": {
        "Line1": {"ProductA": 10, "ProductB": 15, "ProductC": 20},
        "Line2": {"ProductA": 12, "ProductB": 18, "ProductC": 25},
        "Line3": {"ProductA": 11, "ProductB": 16, "ProductC": 19},
    },
    "resources_needed": {
        "ProductA": 5,
        "ProductB": 3,
        "ProductC": 7,
    },
    "available_resources": 50,
}

# Построяване на матрица на разходите
cost_matrix = np.array([
    [production["costs"]["Line1"]["ProductA"], production["costs"]["Line1"]["ProductB"], production["costs"]["Line1"]["ProductC"]],
    [production["costs"]["Line2"]["ProductA"], production["costs"]["Line2"]["ProductB"], production["costs"]["Line2"]["ProductC"]],
    [production["costs"]["Line3"]["ProductA"], production["costs"]["Line3"]["ProductB"], production["costs"]["Line3"]["ProductC"]],
])

# Ресурси и изисквания за продукти
product_requirements = np.array([production["resources_needed"]["ProductA"], production["resources_needed"]["ProductB"], production["resources_needed"]["ProductC"]])
available_resources = production["available_resources"]

# Създаване на разпределение на продуктите (грубо разпределение)
allocation = np.zeros_like(cost_matrix)

remaining_resources = available_resources

# Разпределяне на ресурси между продуктите
for i in range(len(production["lines"])):
    for j in range(len(production["products"])):
        allocated = min(remaining_resources // product_requirements[j], 1)  # Не можем да разпределяме повече от 1 единица
        allocation[i, j] = allocated
        remaining_resources -= allocated * product_requirements[j]

# Изчисляваме общите разходи
total_cost = np.sum(allocation * cost_matrix)
print("Общите разходи за производството:", total_cost)
```

---

### Задача 2: Векторна регресия

**Решение:**
Използваме линейна регресия с два независими променливи (X1 и X2), за да предскажем зависимата променлива Y.

```python
import numpy as np

# Входни данни
data = {
    "X1": [1, 2, 3, 4, 5],
    "X2": [2, 4, 6, 8, 10],
    "Y": [3, 6, 9, 12, 15],
}

# Построяване на матрица за независимите променливи
X = np.vstack([data["X1"], data["X2"], np.ones(len(data["X1"]))]).T
Y = np.array(data["Y"])

# Изчисляваме коефициентите на линейната регресия
coeffs = np.linalg.lstsq(X, Y, rcond=None)[0]
print("Коефициенти на линейната регресия:", coeffs)

# Прогнозиране на стойности
predictions = X @ coeffs
print("Прогнозирани стойности:", predictions)
```

---

### Задача 3: Линейна оптимизация с ограничения

**Решение:**
Ние трябва да разпределим ресурсите между двата продукта така, че да максимизираме печалбата.

```python
import numpy as np

# Входни данни
optimization = {
    "products": ["ProductX", "ProductY"],
    "profits": {"ProductX": 50, "ProductY": 40},
    "resource_requirements": {"ProductX": 3, "ProductY": 2},
    "available_resources": 100,
}

# Построяване на матрица с печалбите и изискванията
profit_matrix = np.array([optimization["profits"]["ProductX"], optimization["profits"]["ProductY"]])
resource_requirements = np.array([optimization["resource_requirements"]["ProductX"], optimization["resource_requirements"]["ProductY"]])
available_resources = optimization["available_resources"]

# Оптимизация на разпределението на ресурсите
# Решение на линейния проблем: максимизиране на печалбата
allocation = np.zeros_like(profit_matrix)

# Изчисляваме колко ресурси можем да разпределим
for i in range(len(profit_matrix)):
    allocation[i] = min(available_resources // resource_requirements[i], 1)  # За всеки продукт, разпределяме ресурси

# Изчисляваме общата печалба
total_profit = np.sum(allocation * profit_matrix)
print("Общата печалба:", total_profit)
```

---

### Задача 4: Прогнозиране на стойности

**Решение:**
Използваме линейна регресия за прогнозиране на бъдещи стойности въз основа на историческите данни.

```python
import numpy as np

# Входни данни
historical_data = {
    "years": [1, 2, 3, 4, 5],
    "performance": [100, 120, 140, 160, 180],
}

# Построяване на матрица на входните данни
X = np.vstack([historical_data["years"], np.ones(len(historical_data["years"]))]).T
Y = np.array(historical_data["performance"])

# Изчисляваме коефициентите на линейната регресия
coeffs = np.linalg.lstsq(X, Y, rcond=None)[0]
print("Коефициенти на линейната регресия:", coeffs)

# Прогнозиране на стойности за следващите 3 години
future_years = np.array([6, 7, 8])
X_future = np.vstack([future_years, np.ones(len(future_years))]).T
predictions = X_future @ coeffs
print("Прогнозирани стойности за следващите 3 години:", predictions)
```

---

### Задача 5: Транспортен проблем с няколко доставчици и потребители

**Решение:**
Ние разпределяме стоките между доставчиците и потребителите, така че разходите да бъдат минимални.

```python
import numpy as np

# Входни данни
transport = {
    "suppliers": ["Supplier1", "Supplier2", "Supplier3"],
    "customers": ["CustomerA", "CustomerB", "CustomerC"],
    "costs": {
        "Supplier1": {"CustomerA": 5, "CustomerB": 8, "CustomerC": 7},
        "Supplier2": {"CustomerA": 6, "CustomerB": 9, "CustomerC": 5},
        "Supplier3": {"CustomerA": 8, "CustomerB": 7, "CustomerC": 6},
    },
    "supply": {"Supplier1": 50, "Supplier2": 60, "Supplier3": 40},
    "demand": {"CustomerA": 30, "CustomerB": 50, "CustomerC": 70},
}

# Построяване на матрица на разходите за транспортиране
cost_matrix = np.array([
    [transport["costs"]["Supplier1"]["CustomerA"], transport["costs"]["Supplier1"]["CustomerB"], transport["costs"]["Supplier1"]["CustomerC"]],
    [transport["costs"]["Supplier2"]["CustomerA"], transport["costs"]["Supplier2"]["CustomerB"], transport["costs"]["Supplier2"]["CustomerC"]],
    [transport["costs"]["Supplier3"]["CustomerA"], transport["costs"]["Supplier3"]["CustomerB"], transport["costs"]["Supplier3"]["CustomerC"]],
])

# Ресурси и търсене
supply = np.array([transport["supply"]["Supplier1"], transport["supply"]["Supplier2"], transport["supply"]["Supplier3"]])
demand = np.array([transport["demand"]["CustomerA"], transport["demand"]["CustomerB"], transport["demand"]["CustomerC"]])

# Разпределение на стоките (грубо разпределение)
allocation = np.zeros_like(cost_matrix)

remaining_supply = supply.copy()
remaining_demand = demand.copy()

# Разпределяме стоките
for i in range(len(supply)):
    for j in range(len(demand)):
        allocated = min(remaining_supply[i], remaining_demand[j])
        allocation[i, j] = allocated
        remaining_supply[i] -= allocated
        remaining_demand[j] -= allocated

# Изчисляваме общите разходи
total_cost = np.sum(allocation * cost_matrix)
print("Общите разходи за транспортиране:", total_cost)
```

---

Тези решения използват само numpy и обясняват основните стъпки

 за решаване на различни типове задачи с линейна алгебра, оптимизация и прогнози. Ако имате въпроси относно тези примери или искате помощ с други задачи, не се колебайте да попитате!

Ето решения за всяка от задачите:

---

### Задача 1: Оптимизация на производствени разходи с ресурси

**Решение:**
Ще използваме линеарни уравнения за разпределяне на ресурсите между продуктите и производствените линии. Ще оптимизираме разходите, като се използва метода на Гаусова елиминация или линеарно програмиране, но с Numpy ще реализираме само разпределението и минимизирането на разходите.

```python
import numpy as np

# Входни данни
optimization = {
    "products": ["ProductA", "ProductB"],
    "production_lines": ["Line1", "Line2"],
    "costs": {
        "Line1": {"ProductA": 10, "ProductB": 15},
        "Line2": {"ProductA": 12, "ProductB": 18},
    },
    "resources_required": {"ProductA": 5, "ProductB": 3},
    "available_resources": 100,
    "maximum_capacity": {"Line1": 40, "Line2": 60},
}

# Строим матрицата на разходите
cost_matrix = np.array([
    [optimization["costs"]["Line1"]["ProductA"], optimization["costs"]["Line1"]["ProductB"]],
    [optimization["costs"]["Line2"]["ProductA"], optimization["costs"]["Line2"]["ProductB"]]
])

# Строим матрицата на необходимите ресурси
resource_matrix = np.array([
    [optimization["resources_required"]["ProductA"], optimization["resources_required"]["ProductB"]],
])

# Прогнозиране на необходимия капитал
required_capital = optimization["available_resources"] / resource_matrix.sum(axis=1)

# Създаваме равенство за разпределение на капацитета
A_eq = np.array([resource_matrix.flatten()])
b_eq = np.array([optimization["available_resources"]])

# Оптимизиране с линейна регресия
# Разпределяме капацитета, като минимизираме разходите
from numpy.linalg import lstsq
x = lstsq(A_eq.T, b_eq, rcond=None)[0]
print("Разпределение на ресурсите:", x)
```

---

### Задача 2: Прогнозиране и оптимизация на продажби

**Решение:**
Ще използваме линейна регресия за прогнозиране на продажбите и оптимизация на рекламния бюджет, като разпределим бюджета за двата продукта. Ще решим проблема чрез минимизация на разходите за реклама и максимизация на прогнозните продажби.

```python
import numpy as np

# Входни данни
sales_optimization = {
    "products": ["ProductX", "ProductY"],
    "advertising_costs": {"ProductX": 30, "ProductY": 40},
    "sales_data": {
        "ProductX": [100, 120, 130, 150, 170],
        "ProductY": [90, 110, 120, 140, 160],
    },
    "advertising_budget": 200,
}

# Исторически данни
sales_x = np.array(sales_optimization["sales_data"]["ProductX"])
sales_y = np.array(sales_optimization["sales_data"]["ProductY"])

# Линейна регресия за прогнозиране на продажбите (по отношение на рекламни разходи)
A = np.vstack([sales_x, np.ones_like(sales_x)]).T
m_x, c_x = np.linalg.lstsq(A, sales_x, rcond=None)[0]
m_y, c_y = np.linalg.lstsq(A, sales_y, rcond=None)[0]

print(f"Прогнозиране за ProductX: y = {m_x}*x + {c_x}")
print(f"Прогнозиране за ProductY: y = {m_y}*x + {c_y}")

# Сега да разпределим рекламния бюджет за максимални продажби
# Ще се опитаме да разпределим бюджета така, че прогнозните продажби да се максимизират
remaining_budget = sales_optimization["advertising_budget"]

# Разпределяме рекламния бюджет равномерно на двете продукти
allocation = remaining_budget / 2
print(f"Рекламен бюджет за ProductX: {allocation}")
print(f"Рекламен бюджет за ProductY: {allocation}")
```

---

### Задача 3: Прогнозиране на възвръщаемост с инвестиции

**Решение:**
Ще оптимизираме разпределението на капитала между два активи въз основа на прогнозирана възвръщаемост, като използваме проста линеарна регресия. След това ще анализираме как да разпределим капитала, за да максимизираме възвръщаемостта.

```python
import numpy as np

# Входни данни
investment = {
    "assets": ["AssetA", "AssetB"],
    "capital_requirements": {"AssetA": 100, "AssetB": 150},
    "returns": {"AssetA": 0.1, "AssetB": 0.15},
    "available_capital": 1000,
    "market_factors": {"AssetA": 1.05, "AssetB": 1.02},
}

# Изчисляваме възвръщаемостта
return_A = investment["returns"]["AssetA"] * investment["market_factors"]["AssetA"]
return_B = investment["returns"]["AssetB"] * investment["market_factors"]["AssetB"]

print(f"Възвръщаемост на AssetA: {return_A}")
print(f"Възвръщаемост на AssetB: {return_B}")

# Изграждаме решението
capital_A = investment["available_capital"] * (return_A / (return_A + return_B))
capital_B = investment["available_capital"] - capital_A

print(f"Разпределение на капитала: {capital_A} за AssetA, {capital_B} за AssetB")
```

---

Тези решения демонстрират как можете да използвате основни Numpy операции и линейна регресия за решаване на по-сложни задачи с две свързани системи. Възможно е да добавите допълнителни ограничения или да използвате различни техники за оптимизация в зависимост от конкретния контекст на задачата.

Разбира се! Ето три нови задачи, като включвам транспониране на матрици и използване на адюгирани количества:

---

### Задача 1: Разпределение на капитала с транспониране на матрица

**Входни данни:**
Имате три различни активи, за които трябва да разпределите капитал от 1500 лв. Капиталът трябва да бъде разпределен пропорционално на възвръщаемостта на всеки актив, като вземем предвид и фактори на риска.

```python
import numpy as np

# Входни данни
investment = {
    "assets": ["AssetA", "AssetB", "AssetC"],
    "capital_requirements": {"AssetA": 100, "AssetB": 150, "AssetC": 200},
    "returns": {"AssetA": 0.1, "AssetB": 0.15, "AssetC": 0.2},
    "available_capital": 1500,
    "risk_factors": {"AssetA": 0.05, "AssetB": 0.08, "AssetC": 0.1},
}

# Изчисляваме възвръщаемостта, като коригираме с рисковите фактори
returns = np.array([investment["returns"]["AssetA"] * (1 - investment["risk_factors"]["AssetA"]),
                    investment["returns"]["AssetB"] * (1 - investment["risk_factors"]["AssetB"]),
                    investment["returns"]["AssetC"] * (1 - investment["risk_factors"]["AssetC"])])

# Транспониране на матрицата за разпределение на капитала
returns_transposed = returns.T

# Изчисляваме разпределението на капитала
capital_allocation = investment["available_capital"] * (returns_transposed / returns_transposed.sum())
print(f"Разпределение на капитала: {capital_allocation}")
```

---

### Задача 2: Оптимизация на производствени линии с адюгирани количества

**Входни данни:**
Имате две производствени линии и два продукта. Всеки продукт изисква определени ресурси от всяка производствена линия. Трябва да изчислите как да разпределите наличните ресурси така, че да минимизирате разходите и да спазите ограниченията на капацитета на линиите.

```python
import numpy as np

# Входни данни
optimization = {
    "products": ["ProductA", "ProductB"],
    "production_lines": ["Line1", "Line2"],
    "costs": {
        "Line1": {"ProductA": 5, "ProductB": 10},
        "Line2": {"ProductA": 7, "ProductB": 12},
    },
    "resources_required": {"ProductA": 3, "ProductB": 4},
    "available_resources": 100,
    "maximum_capacity": {"Line1": 30, "Line2": 40},
}

# Строим матрицата на разходите
cost_matrix = np.array([
    [optimization["costs"]["Line1"]["ProductA"], optimization["costs"]["Line1"]["ProductB"]],
    [optimization["costs"]["Line2"]["ProductA"], optimization["costs"]["Line2"]["ProductB"]]
])

# Строим матрицата на необходимите ресурси
resource_matrix = np.array([
    [optimization["resources_required"]["ProductA"], optimization["resources_required"]["ProductB"]],
])

# Изчисляваме необходимия капитал като адюгираме за разходите
required_capital = np.linalg.inv(cost_matrix).dot(optimization["available_resources"])

# Определяме условията за капацитет
A_eq = np.array([
    resource_matrix.flatten(),
])

b_eq = np.array([optimization["available_resources"]])

# Решаваме оптимизационната задача с помощта на линейна регресия
from numpy.linalg import lstsq
x = lstsq(A_eq.T, b_eq, rcond=None)[0]
print("Разпределение на ресурсите:", x)
```

---

### Задача 3: Прогнозиране на продажби с линейна регресия и транспониране на матрици

**Входни данни:**
Имате две продукта, за които трябва да прогнозирате продажбите на база рекламни разходи и исторически данни. Ще използвате линейна регресия за прогнозиране, а после ще транспонирате матрицата на разходите, за да анализирате ефекта от различни разпределения на бюджета.

```python
import numpy as np

# Входни данни
sales_optimization = {
    "products": ["ProductX", "ProductY"],
    "advertising_costs": {"ProductX": 30, "ProductY": 40},
    "sales_data": {
        "ProductX": [200, 240, 280, 300, 350],
        "ProductY": [150, 170, 190, 220, 250],
    },
    "advertising_budget": 200,
}

# Исторически данни
sales_x = np.array(sales_optimization["sales_data"]["ProductX"])
sales_y = np.array(sales_optimization["sales_data"]["ProductY"])

# Линейна регресия за прогнозиране на продажбите
A = np.vstack([sales_x, np.ones_like(sales_x)]).T
m_x, c_x = np.linalg.lstsq(A, sales_x, rcond=None)[0]
m_y, c_y = np.linalg.lstsq(A, sales_y, rcond=None)[0]

print(f"Прогнозиране за ProductX: y = {m_x}*x + {c_x}")
print(f"Прогнозиране за ProductY: y = {m_y}*x + {c_y}")

# Транспониране на рекламните разходи
advertising_costs = np.array([sales_optimization["advertising_costs"]["ProductX"], 
                              sales_optimization["advertising_costs"]["ProductY"]])
advertising_costs_transposed = advertising_costs.T

# Разпределяме рекламния бюджет за максимални продажби
remaining_budget = sales_optimization["advertising_budget"]

# Разпределяме бюджета пропорционално на рекламните разходи
allocation = remaining_budget * (advertising_costs_transposed / advertising_costs_transposed.sum())
print(f"Рекламен бюджет за ProductX: {allocation[0]}")
print(f"Рекламен бюджет за ProductY: {allocation[1]}")
```

---

Тези задачи обхващат различни аспекти на математическата оптимизация, включително транспониране на матрици и работа с адюгирани количества в контекста на бизнес и производствени проблеми.
