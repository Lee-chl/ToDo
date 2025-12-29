import {API_BASE_URL} from "../api-config";

const ACCESS_TOKEN = "ACCESS_TOKEN";

export function call(api, method, request) {

    let headers = new Headers({
        "Content-Type": "application/json",
    });

    // 로컬 스토리지에서 ACCESS_TOKEN 가져오기
    const accessToken = localStorage.getItem("ACCESS_TOKEN");
    if (accessToken && accessToken !== null) {
        headers.append("Authorization", "Bearer " + accessToken);
    }

    const options = {
        headers: headers,
        url: API_BASE_URL + api,
        method: method,
    };

    if (request) {
        // GET Method
        options.body = JSON.stringify(request);
    }

    return fetch(options.url, options)
        .then((response) => {
                if (!response.ok) {
                    // response.ok가 true 면 정상적인 응답을 받은 것 이고 아니면 에러
                    return Promise.reject(response);
                }
                return response.json();
            }
        ).catch((error) => {
            console.log(error.status);
            if (error.status === 403) {
                window.location.href = "/login"; // redirect
            }
            return Promise.reject(error);
        });
}

export function signin(userDto) {
    return call("/auth/signin", "POST", userDto)
        .then((response) => {
            if (response.token) {
                // 로컬 스토리지에 토큰 저장
                localStorage.setItem("ACCESS_TOKEN", response.token);
                // token이 존재하는 경우 메인 화면으로 리다이렉트
                window.location.href = "/";
            }
        });
}

export function signout() {
    localStorage.setItem(ACCESS_TOKEN, null);
    window.location.href = "/login";
}