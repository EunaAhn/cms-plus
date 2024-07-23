import InputWeb from '@/components/common/inputs/InputWeb';
import FileUpload from '@/components/common/FileUpload';
import { useMemberPaymentStore } from '@/stores/useMemberPaymentStore';
import { formatCardMonthForDisplay, formatCardYearForDisplay } from '@/utils/format/formatCardDate';
import InputCalendar from '@/components/common/inputs/InputCalendar';

const CardMethodForm = ({ paymentMethod, formType }) => {
  const {
    paymentMethodInfoReq_Card,
    setPaymentMethodInfoReq_Card,
    paymentTypeInfoReq_Auto,
    setPaymentTypeInfoReq_Auto,
  } = useMemberPaymentStore();

  // <------ 인풋 필드 입력값 변경 ------>
  const handleChangeInput = e => {
    const { id, value } = e.target;
    if (id === 'cardYear') {
      setPaymentMethodInfoReq_Card({ [id]: formatCardYearForDisplay(value) });
    } else if (id === 'cardMonth') {
      setPaymentMethodInfoReq_Card({ [id]: formatCardMonthForDisplay(value) });
    } else {
      setPaymentMethodInfoReq_Card({ [id]: value });
    }
  };

  // <------ 파일 업로드 ------>
  const handleUploadFile = file => {
    setPaymentTypeInfoReq_Auto({ consentImgUrl: URL.createObjectURL(file) });
    setPaymentTypeInfoReq_Auto({ consetImgName: file.name });
  };

  // TODO
  // <------ 정규표현식 예외처리 ------>

  return (
    <>
      <div className='flex mt-6 mb-6'>
        <div className='flex w-full'>
          <InputWeb
            id='cardNumber'
            label='카드번호'
            placeholder='최대 14자리'
            type='text'
            required
            classContainer='w-[30%]'
            classInput='py-3 pr-20'
            value={paymentMethodInfoReq_Card.cardNumber}
            onChange={handleChangeInput}
          />
          <div className='flex items-end w-[20%] mx-5'>
            <InputWeb
              id='cardMonth'
              label='유효기간'
              placeholder='월'
              type='text'
              required
              classInput='py-3'
              value={paymentMethodInfoReq_Card.cardMonth}
              onChange={handleChangeInput}
            />
            <span className='text-text_black mx-2 mb-3'>/</span>
            <InputWeb
              id='cardYear'
              placeholder='년'
              type='text'
              classInput='py-3 '
              value={paymentMethodInfoReq_Card.cardYear}
              onChange={handleChangeInput}
            />
          </div>
          {/* TODO */}
          {/* 파일 삭제 버튼이 필요함 */}
          <div className='flex flex-1 items-end w-[50%]'>
            <InputWeb
              id='agreement'
              label='동의서'
              placeholder='파일 업로드'
              type='text'
              classContainer='w-3/4'
              classInput='py-3 pr-20'
              value={paymentTypeInfoReq_Auto.consetImgName}
              readOnly
            />
            <FileUpload label='동의서 파일 선택' onFileSelect={handleUploadFile} />
          </div>
        </div>
      </div>
      <div className='flex'>
        <InputWeb
          id='cardOwner'
          label='명의자'
          placeholder='최대20자리'
          type='text'
          required
          classContainer='flex-1'
          classInput='py-3 pr-20'
          value={paymentMethodInfoReq_Card.cardOwner}
          onChange={handleChangeInput}
        />
        <div className='flex flex-1 items-end ml-5'>
          <InputCalendar
            id='cardOwnerBirth'
            label='생년월일'
            placeholder='생년월일8자리'
            required
            height='46px'
            width='100%'
            classContainer='w-3/4'
            value={paymentMethodInfoReq_Card.cardOwnerBirth}
            handleChangeValue={handleChangeInput}
          />
          <button className='h-[46px] w-1/4 bg-mint rounded-lg font-800 text-white transition-all duration-200 hover:bg-mint_hover ml-3'>
            카드 인증
          </button>
        </div>
      </div>
    </>
  );
};

export default CardMethodForm;