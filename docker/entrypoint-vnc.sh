#!/bin/bash
set -e

cd /workspace
mkdir -p bin

echo "[INFO] Kompiliere Java Desktop App für den GUI-Display..."
javac --release 21 -encoding ISO-8859-1 -d bin src/start/*.java || true

echo "[INFO] Starte virtuellen Display-Server (Xvfb)..."
Xvfb :99 -screen 0 1280x1024x24 &
XVFB_PID=$!

sleep 2

echo "[INFO] Starte Fenster-Manager (fluxbox)..."
fluxbox &
WM_PID=$!

sleep 2

echo "[INFO] Starte VNC-Server (x11vnc)..."
x11vnc -display :99 -nopw -forever -shared -quiet -rfbport 5900 &
VNC_PID=$!

sleep 2

echo "[INFO] Starte noVNC-Websocket-Proxy..."
/opt/websockify/run 6080 localhost:5900 --web /opt/noVNC &
WEBSOCKIFY_PID=$!

sleep 2
echo "[INFO] noVNC verfügbar unter: http://localhost:6080/vnc.html"
echo "[INFO] Starte die Java Desktop Anwendung auf DISPLAY=:99..."
DISPLAY=:99 java -cp /workspace/bin start.Main

# Wenn die Java-Anwendung beendet wird, sauber aufräumen
kill "$XVFB_PID" "$WM_PID" "$VNC_PID" "$WEBSOCKIFY_PID" 2>/dev/null || true
