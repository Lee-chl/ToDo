let backendHost;

const hostname = window && window.location && window.location.hostname;

if (hostname === "localhost") {
    backendHost = "http://localhost:1234";
} else {
    // aws 배포 시
    backendHost = "http://api.<도메인>"; // 서브 도메인 추가 후 주소 변경
}

export const API_BASE_URL = `${backendHost}`