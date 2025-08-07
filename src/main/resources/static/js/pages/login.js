
// 로그인 관련 함수들의 모음
const LoginPage = () => {

    // 초기화 함수
    const init = () => {
        console.log("로그인 js가 로딩되었습니다.");
    }

    // return 으로 함수를 내보내면 public 한 함수가 됨
    return {
        init
    };
};

export default LoginPage;