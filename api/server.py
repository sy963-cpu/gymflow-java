"""
GymFlow - Python API Server
Bereitstellung von REST-Endpoints für die Anwendung
"""

from flask import Flask, jsonify, request
from flask_cors import CORS
import os
from datetime import datetime

app = Flask(__name__)
CORS(app)

# ============================================================
# Konfiguration
# ============================================================
app.config['JSON_SORT_KEYS'] = False
app.config['JSONIFY_PRETTYPRINT_REGULAR'] = True

# Datenbankverbindung (optional - benötigt PyMySQL)
DB_HOST = os.getenv('DB_HOST', 'mysql')
DB_USER = os.getenv('DB_USER', 'gymflow_user')
DB_PASSWORD = os.getenv('DB_PASSWORD', 'gymflow_pass')
DB_NAME = os.getenv('DB_NAME', 'gymflow_db')

print(f"[API] Starting GymFlow Python API Server")
print(f"[DB] Connecting to {DB_HOST}:{DB_NAME}")

# ============================================================
# Routes
# ============================================================

@app.route('/', methods=['GET'])
def index():
    """API Root Endpoint"""
    return jsonify({
        'status': 'ok',
        'service': 'GymFlow Python API',
        'version': '1.0.0',
        'timestamp': datetime.now().isoformat(),
        'endpoints': {
            'health': '/health',
            'users': '/api/users',
            'plans': '/api/plans',
            'exercises': '/api/exercises',
            'sessions': '/api/sessions'
        }
    })

@app.route('/health', methods=['GET'])
def health():
    """Health Check Endpoint"""
    return jsonify({
        'status': 'ok',
        'service': 'gymflow-api',
        'timestamp': datetime.now().isoformat()
    })

@app.route('/api/users', methods=['GET'])
def get_users():
    """Alle Nutzer abrufen"""
    return jsonify({
        'status': 'success',
        'count': 2,
        'users': [
            {
                'id': 1,
                'name': 'Max Mustermann',
                'email': 'max@example.de',
                'created_at': '2025-01-15T10:30:00'
            },
            {
                'id': 2,
                'name': 'Anna Schmidt',
                'email': 'anna@example.de',
                'created_at': '2025-02-20T14:45:00'
            }
        ]
    })

@app.route('/api/users/<int:user_id>', methods=['GET'])
def get_user(user_id):
    """Spezifischen Nutzer abrufen"""
    return jsonify({
        'status': 'success',
        'user': {
            'id': user_id,
            'name': 'Max Mustermann',
            'email': f'user{user_id}@example.de',
            'created_at': '2025-01-15T10:30:00'
        }
    })

@app.route('/api/plans', methods=['GET'])
def get_plans():
    """Alle Trainingspläne abrufen"""
    return jsonify({
        'status': 'success',
        'count': 2,
        'plans': [
            {
                'id': 1,
                'user_id': 1,
                'name': 'Ganzkörpertraining',
                'description': 'Trainingsplan für alle Muskelgruppen',
                'exercises_count': 2,
                'created_at': '2025-01-16'
            },
            {
                'id': 2,
                'user_id': 2,
                'name': 'Oberkörper Fokus',
                'description': 'Schwerpunkt auf Brust und Schultern',
                'exercises_count': 1,
                'created_at': '2025-02-21'
            }
        ]
    })

@app.route('/api/exercises', methods=['GET'])
def get_exercises():
    """Alle Übungen abrufen"""
    return jsonify({
        'status': 'success',
        'count': 3,
        'exercises': [
            {
                'id': 1,
                'plan_id': 1,
                'name': 'Bankdrücken',
                'equipment': 'Bank',
                'sets': 4,
                'reps': 8,
                'weight': 100.00
            },
            {
                'id': 2,
                'plan_id': 1,
                'name': 'Kniebeugen',
                'equipment': 'Barbell',
                'sets': 4,
                'reps': 10,
                'weight': 120.00
            },
            {
                'id': 3,
                'plan_id': 2,
                'name': 'Schulterdrücken',
                'equipment': 'Dumbbell',
                'sets': 3,
                'reps': 10,
                'weight': 30.00
            }
        ]
    })

@app.route('/api/sessions', methods=['GET'])
def get_sessions():
    """Alle Trainingseinheiten abrufen"""
    return jsonify({
        'status': 'success',
        'count': 0,
        'sessions': []
    })

@app.route('/api/test-db', methods=['GET'])
def test_database():
    """Testet die Datenbankverbindung"""
    try:
        import pymysql
        
        connection = pymysql.connect(
            host=DB_HOST,
            user=DB_USER,
            password=DB_PASSWORD,
            database=DB_NAME
        )
        
        with connection.cursor() as cursor:
            cursor.execute("SELECT COUNT(*) as count FROM users")
            result = cursor.fetchone()
            user_count = result[0] if result else 0
        
        connection.close()
        
        return jsonify({
            'status': 'success',
            'database': 'connected',
            'user_count': user_count
        })
    except Exception as e:
        return jsonify({
            'status': 'error',
            'database': 'disconnected',
            'error': str(e)
        }), 500

# ============================================================
# Error Handling
# ============================================================

@app.errorhandler(404)
def not_found(error):
    return jsonify({
        'status': 'error',
        'message': 'Endpoint nicht gefunden',
        'path': request.path
    }), 404

@app.errorhandler(500)
def server_error(error):
    return jsonify({
        'status': 'error',
        'message': 'Interner Serverfehler'
    }), 500

# ============================================================
# Startup
# ============================================================

if __name__ == '__main__':
    print("[API] Flask API Server started")
    print("[API] Listening on 0.0.0.0:5000")
    print("[API] CORS enabled for all origins")
    app.run(host='0.0.0.0', port=5000, debug=True)
