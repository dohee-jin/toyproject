
// 회원가입 관련 함수들의 모음
const SignupPage = () => {

    // 상태 관리 객체
    const state = {
        $form : null,
        $singUpBtn: null,
        $usernameInput: null,
        $emailInput: null,
        $passwordInput: null,
        $confirmPasswordInput: null,
    };

    // form 제출 이벤트
    const handleSubmit = e => {
        e.preventDefault();
        console.log('폼이 제출됨');
    }

    // 이벤트 걸기
    const bindEvents = () => {
        // 1. form 제출 이벤트
        state.$form.addEventListener('submit', handleSubmit);
    }

    // 초기화 함수
    const init = () => {
        console.log("회원가입 js가 로딩되었습니다.");

        // 필요한 DOM 들을 가져오기
        state.$form = document.getElementById('signupForm');
        state.$signupBtn = document.getElementById('signupBtn');
        state.$usernameInput = document.getElementById('usernameInput');
        state.$emailInput = document.getElementById('email');
        state.$passwordInput = document.getElementById('password');
        state.$confirmPasswordInput = document.getElementById('confirmPassword');

        // 이벤트 바인딩
        bindEvents();

    }

    return {
        init
    };
};

export default SignupPage;