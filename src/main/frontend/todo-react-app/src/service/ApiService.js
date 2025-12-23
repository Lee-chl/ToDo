import {API_BASE_URL} from "../api-config";

export function call(api, method, request) {
    const options = {
        headers: new Headers({
            "Content-Type": "application/json",
        }),
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
            console.log("response: ", response);
            alert("로그인 토큰: " + response.token);
        });
}