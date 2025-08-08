import{apiService} from "../utils/api.js";
import{utils} from "../utils/util.js";

// 로그인 관련 함수들의 모음
const LoginPage = () => {

    const state = {
        $form: null
    }

    // 로그인 이벤트
    const handleLogin = async (e) => {
        e.preventDefault();

        // 폼 입력값 읽기
        const formData = new FormData(state.$form);
        const payload = Object.fromEntries(formData.entries());
        console.log(`entries:`, payload);

        try {

            const response = await apiService.post('api/auth/login', payload);
            utils.showMessage(response.message, 'success');

        } catch (error) {
            utils.showMessage(error.message, 'error');
        }

    }

    // 이벤트 걸기
    const bindEvents = () => {
        state.$form?.addEventListener('submit', handleLogin);
    }

    // 초기화 함수
    const init = () => {

        console.log("로그인 js가 로딩되었습니다.");

        state.$form = document.getElementById('loginForm');

        bindEvents();
    }

    // return 으로 함수를 내보내면 public 한 함수가 됨
    return {
        init
    };
};

export default LoginPage;