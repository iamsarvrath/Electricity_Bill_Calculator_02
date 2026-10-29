<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Electricity Bill Calculator</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<main class="page-shell">
    <section class="calculator-card" aria-labelledby="page-title">
        <div class="intro">
            <span class="eyebrow">UTILITY TOOL</span>
            <h1 id="page-title">Electricity bill calculator</h1>
            <p>Enter your monthly consumption to estimate your electricity bill.</p>
        </div>

        <form class="bill-form" action="${pageContext.request.contextPath}/calculate" method="post">
            <label for="units">Units consumed</label>
            <div class="input-row">
                <input id="units" name="units" type="number" min="0" step="1"
                       placeholder="e.g. 180" value="${units}" required>
                <span>kWh</span>
            </div>
            <button type="submit">Calculate bill <span aria-hidden="true">→</span></button>
        </form>

        <% if (request.getAttribute("error") != null) { %>
            <p class="message error" role="alert">${error}</p>
        <% } %>

        <% if (request.getAttribute("bill") != null) { %>
            <div class="result" aria-live="polite">
                <div>
                    <span class="result-label">Estimated total</span>
                    <strong>₹${bill}</strong>
                </div>
                <span class="result-units">${units} units</span>
            </div>
        <% } %>

        <div class="rates">
            <h2>Tariff slabs</h2>
            <div class="rate-row"><span>First 50 units</span><strong>₹3.50 / unit</strong></div>
            <div class="rate-row"><span>Next 100 units</span><strong>₹4.00 / unit</strong></div>
            <div class="rate-row"><span>Next 100 units</span><strong>₹5.20 / unit</strong></div>
            <div class="rate-row"><span>Above 250 units</span><strong>₹6.50 / unit</strong></div>
        </div>
    </section>
    <footer>Calculated using the current tariff slabs</footer>
</main>
</body>
</html>
