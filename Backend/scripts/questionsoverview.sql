-- SELECT
-- `Questions`.`Id`, `Questions`.`Title`, `Questions`.`Submit`,
-- `Questions`.`Choice1`, `Questions`.`Choice2`, `Questions`.`Choice3`, `Questions`.`Choice4`,
-- Count(`Answers`.`Id`) as answer
-- FROM `Questions`
--     LEFT JOIN `Answers` ON
--         `Answers`.`QuestionId` = `Questions`.`Id`
--     GROUP BY
--     `Questions`.`Id`, `Questions`.`Title`, `Questions`.`Submit`,
--     `Questions`.`Choice1`, `Questions`.`Choice2`, `Questions`.`Choice3`, `Questions`.`Choice4`

SELECT
`Questions`.*,
(SELECT count(*) FROM `Answers` WHERE `Answers`.`Choice` = 1 AND `Answers`.`QuestionId` = `Questions`.`Id`) as Choice1Count,
(SELECT count(*) FROM `Answers` WHERE `Answers`.`Choice` = 2 AND `Answers`.`QuestionId` = `Questions`.`Id`) as Choice2Count,
(SELECT count(*) FROM `Answers` WHERE `Answers`.`Choice` = 3 AND `Answers`.`QuestionId` = `Questions`.`Id`) as Choice3Count,
(SELECT count(*) FROM `Answers` WHERE `Answers`.`Choice` = 4 AND `Answers`.`QuestionId` = `Questions`.`Id`) as Choice4Count
FROM `Questions`
ORDER BY `Id` DESC