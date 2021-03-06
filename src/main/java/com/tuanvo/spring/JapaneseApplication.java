package com.tuanvo.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.tuanvo.spring.entity.Role;
import com.tuanvo.spring.entity.User;
import com.tuanvo.spring.entity.Vocab;
import com.tuanvo.spring.entity.Word;
import com.tuanvo.spring.entity.WordTopic;
import com.tuanvo.spring.service.IRoleService;
import com.tuanvo.spring.service.IService;
import com.tuanvo.spring.service.IVocabService;
import com.tuanvo.spring.service.IWordService;
import com.tuanvo.spring.service.IWordTopicService;
import com.tuanvo.spring.utils.ConstantUtils;

@SpringBootApplication
public class JapaneseApplication implements CommandLineRunner {
	@Autowired
	private IService<User> userService;

	@Autowired
	private IRoleService<Role> roleService;
	
	@Autowired
	private IWordTopicService<WordTopic> wordTopicService;

	@Autowired
	private IWordService<Word> wordService;

	@Autowired
	private IVocabService<Vocab> vocabService;

	public static void main(String[] args) {
		SpringApplication.run(JapaneseApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		if (roleService.findAll().isEmpty()) {
			roleService.saveOrUpdate(new Role(ConstantUtils.ADMIN.toString()));
			roleService.saveOrUpdate(new Role(ConstantUtils.USER.toString()));
		}

		if (userService.findAll().isEmpty()) {
			User user1 = new User();
			user1.setEmail("user@gmail.com");
			user1.setName("Test User");
			user1.setMobile("9787456545");
			user1.setRole(roleService.findByName(ConstantUtils.USER.toString()));
			user1.setPassword(new BCryptPasswordEncoder().encode("user"));
			userService.saveOrUpdate(user1);

			User user2 = new User();
			user2.setEmail("admin@gmail.com");
			user2.setName("Test Admin");
			user2.setMobile("9787456545");
			user2.setRole(roleService.findByName(ConstantUtils.ADMIN.toString()));
			user2.setPassword(new BCryptPasswordEncoder().encode("admin"));
			userService.saveOrUpdate(user2);
		}
		if (wordTopicService.findAll().isEmpty()) {
			wordTopicService.saveOrUpdate(new WordTopic("An to??n th???c ph???m"));
			wordTopicService.saveOrUpdate(new WordTopic("??m nh???c"));
			wordTopicService.saveOrUpdate(new WordTopic("C???m gi??c v?? c???m x??c"));
			wordTopicService.saveOrUpdate(new WordTopic("C??n tr??ng v?? b?? s??t"));
			wordTopicService.saveOrUpdate(new WordTopic("C??ng ngh???"));
			wordTopicService.saveOrUpdate(new WordTopic("Giao th??ng"));
			wordTopicService.saveOrUpdate(new WordTopic("C?? th??? con ng?????i"));
		}
		if (wordService.findAll().isEmpty()) {
			Word word = new Word();
			word.setTitle("Ph????ng ti???n giao th??ng");
			word.setPhotoURL(
					"https://i.imgur.com/rzDgyHq.jpg");
			word.setQuantity(10);
			word.setTopic(wordTopicService.findByName("Giao th??ng"));
			wordService.saveOrUpdate(word);
			
			for (int i = 1; i <= 4; i++) {
				word = new Word();
				word.setTitle("T??? v???ng ph???n " + i);
				word.setPhotoURL(
						"https://i.imgur.com/qHDd7GM.jpg");
				word.setQuantity(20);
				word.setTopic(wordTopicService.findByName("??m nh???c"));
				wordService.saveOrUpdate(word);
			}
		}
		if (vocabService.findAll().isEmpty()) {
			for (int i = 1; i <= 20; i++) {
				Vocab vocab = new Vocab();
				vocab.setKanji("?????????");
				vocab.setKana("??????????????????");
				vocab.setRomaji("tekkingumi");
				vocab.setMeaning("Bu???c s???t");
				vocab.setImg(
						"https://i.imgur.com/qHDd7GM.jpg");
				vocab.setAudio("https://storage.dekiru.vn/Data/2017/08/31/katawakutekkyo-636397873296849216.mp3");
				vocab.setExample("?????????2");
				vocab.setExampleMeaning("alo2");
				vocab.setWord(wordService.findByTitle("T??? v???ng ph???n 1"));
				vocab.setExampleImg("https://i.imgur.com/rzDgyHq.jpg");
				vocab.setExampleAudio("https://storage.dekiru.vn/Data/2017/08/31/tansokou-636397873339855302.mp3");
				vocabService.saveOrUpdate(vocab);
			}
			Vocab vocab = new Vocab();
			vocab.setKanji("???");
			vocab.setKana("?????????");
			vocab.setRomaji("kuruma");
			vocab.setMeaning("?? t??");
			vocab.setImg("https://i.imgur.com/LOzuRCO.png");
			vocab.setAudio("https://storage.dekiru.vn///Data/2017/03/20/kuruma-636256113065875074.mp3");
			vocab.setExample("????????????????????????????????????");
			vocab.setExampleMeaning("Xe c???a t??i l?? xe c???a h??ng Toyota");
			vocab.setExampleImg(
					"https://i.imgur.com/eMgr0qV.png");
			vocab.setExampleAudio("https://storage.dekiru.vn/Data/2017/06/29/10-636343316260167042.mp3");
			vocab.setWord(wordService.findByTitle("Ph????ng ti???n giao th??ng"));
			vocabService.saveOrUpdate(vocab);
			
			vocab = new Vocab();
			vocab.setKanji("?????????");
			vocab.setKana("???????????????");
			vocab.setRomaji("jitensha");
			vocab.setMeaning("Xe ?????p");
			vocab.setImg("https://i.imgur.com/pnJQXom.png");
			vocab.setAudio("https://storage.dekiru.vn///Data/2017/02/16/jitensha-636228528694116318.mp3");
			vocab.setExample("?????????????????????????????????????????????");
			vocab.setExampleMeaning("H??ng s??ng t??i ??i t???i tr?????ng b???ng xe ?????p.");
			vocab.setExampleImg(
					"https://i.imgur.com/crk0ckK.png");
			vocab.setExampleAudio("https://storage.dekiru.vn/Data/2017/06/29/9-636343316259917348.mp3");
			vocab.setWord(wordService.findByTitle("Ph????ng ti???n giao th??ng"));
			vocabService.saveOrUpdate(vocab);
			
			vocab = new Vocab();
			vocab.setKanji("??????");
			vocab.setKana("????????????");
			vocab.setRomaji("densha");
			vocab.setMeaning("T??u ??i???n");
			vocab.setImg("https://i.imgur.com/wIfloqj.jpg");
			vocab.setAudio("https://storage.dekiru.vn///Data/2017/02/10/densha-636223432288011997.mp3");
			vocab.setExample("?????????????????????????????????????????????");
			vocab.setExampleMeaning("C??c chuy???n t??u ch???y c??ch nhau 30 ph??t.");
			vocab.setExampleImg(
					"https://i.imgur.com/cHEWR4V.jpg");
			vocab.setExampleAudio("https://storage.dekiru.vn/Data/2017/06/29/6-636343316274439154.mp3");
			vocab.setWord(wordService.findByTitle("Ph????ng ti???n giao th??ng"));
			vocabService.saveOrUpdate(vocab);
			
			vocab = new Vocab();
			vocab.setKanji("??????");
			vocab.setKana("??????");
			vocab.setRomaji("basu");
			vocab.setMeaning("Xe bu??t");
			vocab.setImg("https://i.imgur.com/6K4e8fv.jpg");
			vocab.setAudio("https://storage.dekiru.vn///Data/2017/03/20/basu-636256115354789119.mp3");
			vocab.setExample("??????????????????????????????????????????");
			vocab.setExampleMeaning("Anh ???y ?????n tr?????ng b???ng t??u ??i???n ho???c xe bu??t.");
			vocab.setExampleImg(
					"https://i.imgur.com/4QtHdFY.jpg");
			vocab.setExampleAudio("https://storage.dekiru.vn/Data/2017/06/29/7-636343316259486936.mp3");
			vocab.setWord(wordService.findByTitle("Ph????ng ti???n giao th??ng"));
			vocabService.saveOrUpdate(vocab);
			
			vocab = new Vocab();
			vocab.setKanji("?????????");
			vocab.setKana("????????????????????????");
			vocab.setRomaji("kyuukyuusha");
			vocab.setMeaning("Xe c???p c???u");
			vocab.setImg("https://i.imgur.com/a9i1IJv.jpg");
			vocab.setAudio("https://storage.dekiru.vn/Data/2017/07/11/kyuukyuusha-636353809614627950.mp3");
			vocab.setExample("????????????????????????????????????");
			vocab.setExampleMeaning("H??y g???i xe c???p c???u ??i!");
			vocab.setExampleImg(
					"https://i.imgur.com/yoSJGP5.jpg");
			vocab.setExampleAudio("https://storage.dekiru.vn/Data/2017/06/29/1-636343316258906856.mp3");
			vocab.setWord(wordService.findByTitle("Ph????ng ti???n giao th??ng"));
			vocabService.saveOrUpdate(vocab);
			
			vocab = new Vocab();
			vocab.setKanji("???");
			vocab.setKana("??????");
			vocab.setRomaji("fune");
			vocab.setMeaning("Thuy???n");
			vocab.setImg("https://i.imgur.com/b8EVqlH.jpg");
			vocab.setAudio("https://storage.dekiru.vn///Data/2017/03/20/fune-636256117722717189.mp3");
			vocab.setExample("????????????????????????????????????");
			vocab.setExampleMeaning("Con thuy???n ??ang h?????ng v??? ph??a T??y .");
			vocab.setExampleImg(
					"https://i.imgur.com/dDpTEQs.jpg");
			vocab.setExampleAudio("https://storage.dekiru.vn/Data/2017/06/29/3-636343316259277402.mp3");
			vocab.setWord(wordService.findByTitle("Ph????ng ti???n giao th??ng"));
			vocabService.saveOrUpdate(vocab);
			
			vocab = new Vocab();
			vocab.setKanji("?????????");
			vocab.setKana("????????????");
			vocab.setRomaji("hikouki");
			vocab.setMeaning("M??y bay");
			vocab.setImg("https://i.imgur.com/dwQqwP3.jpg");
			vocab.setAudio("https://storage.dekiru.vn///Data/2017/02/10/hikoki-636223432308172123.mp3");
			vocab.setExample("??????????????????????????????????????????????????????");
			vocab.setExampleMeaning("Anh Sato ???? l??n m??y bay s??ng nay.");
			vocab.setExampleImg(
					"https://i.imgur.com/U693QCP.jpg");
			vocab.setExampleAudio("https://storage.dekiru.vn/Data/2017/06/29/4-636343316289581397.mp3");
			vocab.setWord(wordService.findByTitle("Ph????ng ti???n giao th??ng"));
			vocabService.saveOrUpdate(vocab);
			
			vocab = new Vocab();
			vocab.setKanji("????????????");
			vocab.setKana("????????????");
			vocab.setRomaji("patokaa");
			vocab.setMeaning("Xe c???nh s??t");
			vocab.setImg("https://i.imgur.com/PAxDul4.jpg");
			vocab.setAudio("https://storage.dekiru.vn/Data/2017/07/11/patokaa-636353809641331876.mp3");
			vocab.setExample("???????????????????????????????????????????????????????????????");
			vocab.setExampleMeaning("Xe c???nh s??t ????? ph??a tr?????c chung c??.");
			vocab.setExampleImg(
					"https://i.imgur.com/nbj9CZk.jpg");
			vocab.setExampleAudio("https://storage.dekiru.vn/Data/2017/06/29/2-636343316304603672.mp3");
			vocab.setWord(wordService.findByTitle("Ph????ng ti???n giao th??ng"));
			vocabService.saveOrUpdate(vocab);
			
			vocab = new Vocab();
			vocab.setKanji("?????????");
			vocab.setKana("?????????");
			vocab.setRomaji("baiku");
			vocab.setMeaning("Xe m?? t??");
			vocab.setImg("https://i.imgur.com/493Xde6.jpg");
			vocab.setAudio("https://storage.dekiru.vn///Data/2017/03/20/baiku-636256114309194513.mp3");
			vocab.setExample("??????????????????????????????");
			vocab.setExampleMeaning("T??i ???? s??n m??u ????? cho chi???c xe m?? t??.");
			vocab.setExampleImg(
					"https://i.imgur.com/l8zm9A3.jpg");
			vocab.setExampleAudio("https://storage.dekiru.vn/Data/2017/06/29/8-636343316259706972.mp3");
			vocab.setWord(wordService.findByTitle("Ph????ng ti???n giao th??ng"));
			vocabService.saveOrUpdate(vocab);
			
			vocab = new Vocab();
			vocab.setKanji("????????????");
			vocab.setKana("????????????	");
			vocab.setRomaji("takushii");
			vocab.setMeaning("Xe taxi");
			vocab.setImg("https://i.imgur.com/kDAhQNp.png");
			vocab.setAudio("https://storage.dekiru.vn///Data/2017/03/20/takushii-636256116084059371.mp3");
			vocab.setExample("??????????????????????????????????????????????????????");
			vocab.setExampleMeaning("G???n nh?? ga c?? ??i???m b???t taxi.");
			vocab.setExampleImg(
					"https://i.imgur.com/qz23NeD.png");
			vocab.setExampleAudio("https://storage.dekiru.vn/Data/2017/06/29/5-636343316284580664.mp3");
			vocab.setWord(wordService.findByTitle("Ph????ng ti???n giao th??ng"));
			vocabService.saveOrUpdate(vocab);
		}

	}

}
