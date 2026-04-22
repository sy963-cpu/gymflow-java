<?php
/**
 * GymFlow - Web Anwendung
 * Startseite und Navigationszentrale
 */

// Datenbankverbindung
$db_host = getenv('DB_HOST') ?: 'localhost';
$db_user = getenv('DB_USER') ?: 'root';
$db_pass = getenv('DB_PASSWORD') ?: '';
$db_name = getenv('DB_NAME') ?: 'gymflow_db';

$connection_status = "unbekannt";
$user_count = 0;

try {
    $conn = new mysqli($db_host, $db_user, $db_pass, $db_name);
    
    if ($conn->connect_error) {
        $connection_status = "Fehler: " . $conn->connect_error;
    } else {
        $connection_status = "Verbunden";
        
        // Nutzer zählen
        $result = $conn->query("SELECT COUNT(*) as count FROM users");
        if ($result) {
            $row = $result->fetch_assoc();
            $user_count = $row['count'];
        }
    }
} catch (Exception $e) {
    $connection_status = "Fehler: " . $e->getMessage();
}
?>

<!DOCTYPE html>
<html lang="de">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>GymFlow - Fitness Tracking System</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }
        
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            min-height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center;
            padding: 20px;
        }
        
        .container {
            background: white;
            border-radius: 10px;
            box-shadow: 0 10px 40px rgba(0, 0, 0, 0.3);
            max-width: 1200px;
            width: 100%;
            padding: 40px;
        }
        
        h1 {
            color: #333;
            margin-bottom: 10px;
            display: flex;
            align-items: center;
            gap: 15px;
        }
        
        .logo {
            font-size: 40px;
        }
        
        .subtitle {
            color: #666;
            margin-bottom: 30px;
        }
        
        .status-grid {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
            gap: 20px;
            margin-bottom: 40px;
        }
        
        .status-card {
            background: #f8f9fa;
            padding: 20px;
            border-radius: 8px;
            border-left: 4px solid #667eea;
        }
        
        .status-card h3 {
            font-size: 14px;
            color: #666;
            text-transform: uppercase;
            margin-bottom: 10px;
        }
        
        .status-value {
            font-size: 24px;
            font-weight: bold;
            color: #333;
        }
        
        .status-card.success {
            border-left-color: #28a745;
        }
        
        .status-card.error {
            border-left-color: #dc3545;
        }
        
        .services {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));
            gap: 15px;
            margin-bottom: 30px;
        }
        
        .service-link {
            display: block;
            padding: 20px;
            background: #f8f9fa;
            border-radius: 8px;
            text-decoration: none;
            color: #333;
            border: 2px solid #ddd;
            transition: all 0.3s;
            text-align: center;
        }
        
        .service-link:hover {
            border-color: #667eea;
            background: #f0f4ff;
            color: #667eea;
        }
        
        .service-link strong {
            display: block;
            margin-bottom: 5px;
        }
        
        .port {
            font-size: 12px;
            color: #999;
        }
        
        footer {
            border-top: 1px solid #ddd;
            padding-top: 20px;
            margin-top: 30px;
            color: #666;
            font-size: 14px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>
            <span class="logo">💪</span>
            <span>GymFlow</span>
        </h1>
        <p class="subtitle">Vollintegriertes Fitness Tracking System</p>
        
        <h2 style="margin-top: 30px; margin-bottom: 20px; font-size: 18px;">System Status</h2>
        
        <div class="status-grid">
            <div class="status-card <?php echo strpos($connection_status, 'Verbunden') !== false ? 'success' : 'error'; ?>">
                <h3>🗄️ Datenbank</h3>
                <div class="status-value"><?php echo strpos($connection_status, 'Verbunden') !== false ? 'Online' : 'Offline'; ?></div>
                <p><?php echo $connection_status; ?></p>
            </div>
            
            <div class="status-card success">
                <h3>🌐 Web Server</h3>
                <div class="status-value">Online</div>
                <p>PHP 8.2 mit Apache</p>
            </div>
            
            <div class="status-card">
                <h3>👥 Benutzer</h3>
                <div class="status-value"><?php echo $user_count; ?></div>
                <p>In der Datenbank registriert</p>
            </div>
        </div>
        
        <h2 style="margin-bottom: 20px; font-size: 18px;">Verfügbare Services</h2>
        
        <div class="services">
            <a href="http://localhost:5000" class="service-link" target="_blank">
                <strong>🐍 Python API</strong>
                <span class="port">http://localhost:5000</span>
            </a>
            
            <a href="http://localhost:3000" class="service-link" target="_blank">
                <strong>🟩 Node.js App</strong>
                <span class="port">http://localhost:3000</span>
            </a>
            
            <a href="http://localhost:8081" class="service-link" target="_blank">
                <strong>🗄️ phpMyAdmin</strong>
                <span class="port">http://localhost:8081</span>
            </a>
            
            <a href="api/test.php" class="service-link">
                <strong>🧪 API Test</strong>
                <span class="port">Lokale Endpoints</span>
            </a>
        </div>
        
        <h2 style="margin-bottom: 15px; font-size: 18px;">Quick Links</h2>
        
        <ul style="list-style: none; margin-bottom: 30px;">
            <li style="margin-bottom: 10px;">
                📖 <a href="https://github.com/sy963-cpu/gymflow-java" target="_blank" style="color: #667eea;">GitHub Repository</a>
            </li>
            <li style="margin-bottom: 10px;">
                📚 <a href="#" style="color: #667eea;">Dokumentation (lokal)</a>
            </li>
            <li style="margin-bottom: 10px;">
                🧪 <a href="#" style="color: #667eea;">Test Suite starten</a>
            </li>
        </ul>
        
        <footer>
            <strong>GymFlow v1.0</strong> - Fitness Tracking & Training Planner<br>
            <?php echo date('Y-m-d H:i:s'); ?> | Server: <?php echo php_uname(); ?>
        </footer>
    </div>
</body>
</html>
