let backendHost;

const hostname = window && window.location && window.location.hostname;

if (hostname === "localhost") {
    backendHost = "http://localhost:1234";
} else {
    // aws 배포 시
    backendHost = "http://prod-todo-backend.us-west-2.elasticbeanstalk.com"; // aws 주소로 변경
}

export const API_BASE_URL = `${backendHost}`