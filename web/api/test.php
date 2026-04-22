<?php
/**
 * GymFlow - API Test & Debug Seite
 * Zeigt verfügbare API-Endpoints und deren Status
 */

header('Content-Type: application/json');

$db_host = getenv('DB_HOST') ?: 'localhost';
$db_user = getenv('DB_USER') ?: 'root';
$db_pass = getenv('DB_PASSWORD') ?: '';
$db_name = getenv('DB_NAME') ?: 'gymflow_db';

$response = [
    'status' => 'ok',
    'timestamp' => date('c'),
    'php_version' => PHP_VERSION,
    'extensions' => [
        'pdo' => extension_loaded('pdo'),
        'pdo_mysql' => extension_loaded('pdo_mysql'),
        'mysqli' => extension_loaded('mysqli'),
        'json' => extension_loaded('json'),
        'curl' => extension_loaded('curl'),
    ]
];

// Datenbank Test
try {
    $conn = new mysqli($db_host, $db_user, $db_pass, $db_name);
    if ($conn->connect_error) {
        $response['database'] = [
            'status' => 'error',
            'error' => $conn->connect_error
        ];
    } else {
        $result = $conn->query('SELECT COUNT(*) as count FROM users');
        $row = $result->fetch_assoc();
        $response['database'] = [
            'status' => 'connected',
            'host' => $db_host,
            'database' => $db_name,
            'users' => $row['count']
        ];
        $conn->close();
    }
} catch (Exception $e) {
    $response['database'] = [
        'status' => 'error',
        'error' => $e->getMessage()
    ];
}

// API Endpoints
$response['api_endpoints'] = [
    'GET /api/users' => 'Alle Benutzer abrufen',
    'GET /api/plans' => 'Alle Trainingspläne',
    'GET /api/exercises' => 'Alle Übungen',
    'GET /api/sessions' => 'Trainingseinheiten',
];

// Python API Status
$python_api = @file_get_contents('http://python-api:5000/health', false, stream_context_create(['http' => ['timeout' => 2]]));
$response['python_api'] = $python_api ? 'available' : 'unavailable';

echo json_encode($response, JSON_PRETTY_PRINT | JSON_UNESCAPED_SLASHES);
?>
